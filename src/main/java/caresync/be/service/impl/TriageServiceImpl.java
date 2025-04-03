package caresync.be.service.impl;

import caresync.be.domain.ABCDEAssessment;
import caresync.be.domain.TriageRecord;
import caresync.be.service.TriageService;
import org.springframework.stereotype.Service;

@Service
public class TriageServiceImpl implements TriageService {

    public int assignPriorityCombined(TriageRecord triageRecord) {
        if (isImmediateEmergency(triageRecord.getAbcdeAssessment())) {
            return 0;
        }

        int priority = 5;

        priority = assessAirway(triageRecord.getAbcdeAssessment().getAirway(), priority);
        priority = assessBreathing(triageRecord.getAbcdeAssessment().getBreathing(), priority);
        priority = assessCirculation(triageRecord.getAbcdeAssessment().getCirculation(), priority);
        priority = assessDisability(triageRecord.getAbcdeAssessment().getDisability(), priority);
        priority = assessExposure(triageRecord.getAbcdeAssessment().getExposure(), priority);

        return priority;
    }

    private boolean isImmediateEmergency(ABCDEAssessment abcdeAssessment) {
        return "blocked".equalsIgnoreCase(abcdeAssessment.getAirway()) ||
                "no_breathing".equalsIgnoreCase(abcdeAssessment.getBreathing()) ||
                "no_pulse".equalsIgnoreCase(abcdeAssessment.getCirculation()) ||
                "severe_bleeding".equalsIgnoreCase(abcdeAssessment.getCirculation()) ||
                "unresponsive".equalsIgnoreCase(abcdeAssessment.getDisability()) ||
                "critical".equalsIgnoreCase(abcdeAssessment.getExposure());
    }

    private int assessAirway(String airway, int currentPriority) {
        if ("partial".equalsIgnoreCase(airway)) return Math.min(currentPriority, 1);
        return currentPriority;
    }

    private int assessBreathing(String breathing, int currentPriority) {
        if ("severe_distress".equalsIgnoreCase(breathing)) return Math.min(currentPriority, 1);
        if ("mild_distress".equalsIgnoreCase(breathing)) return Math.min(currentPriority, 2);
        return currentPriority;
    }

    private int assessCirculation(String circulation, int currentPriority) {
        if ("weak_pulse".equalsIgnoreCase(circulation)) return Math.min(currentPriority, 1);
        if ("mild_bleeding".equalsIgnoreCase(circulation)) return Math.min(currentPriority, 2);
        if ("stable".equalsIgnoreCase(circulation)) return Math.min(currentPriority, 3);
        return currentPriority;
    }

    private int assessDisability(String disability, int currentPriority) {
        if ("responding_to_pain".equalsIgnoreCase(disability)) return Math.min(currentPriority, 1);
        if ("responsive_to_verbal".equalsIgnoreCase(disability)) return Math.min(currentPriority, 2);
        if ("alert".equalsIgnoreCase(disability)) return Math.min(currentPriority, 4);
        return currentPriority;
    }

    private int assessExposure(String exposure, int currentPriority) {
        if ("severe".equalsIgnoreCase(exposure)) return Math.min(currentPriority, 1);
        if ("moderate".equalsIgnoreCase(exposure)) return Math.min(currentPriority, 2);
        if ("mild".equalsIgnoreCase(exposure)) return Math.min(currentPriority, 3);
        if ("minor".equalsIgnoreCase(exposure)) return Math.min(currentPriority, 4);
        return currentPriority;
    }
}
