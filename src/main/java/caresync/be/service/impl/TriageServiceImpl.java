package caresync.be.service.impl;

import caresync.be.service.TriageService;
import org.springframework.stereotype.Service;

@Service
public class TriageServiceImpl implements TriageService {

    public int assignPriorityCombined(String airway, String breathing, String circulation, String disability, String exposure) {
        if (isImmediateEmergency(airway, breathing, circulation, disability, exposure)) {
            return 0;
        }

        int priority = 5;

        // Process each vital sign and adjust priority as needed
        priority = assessAirway(airway, priority);
        priority = assessBreathing(breathing, priority);
        priority = assessCirculation(circulation, priority);
        priority = assessDisability(disability, priority);
        priority = assessExposure(exposure, priority);

        return priority;
    }

    private boolean isImmediateEmergency(String airway, String breathing, String circulation, String disability, String exposure) {
        return "blocked".equalsIgnoreCase(airway) ||
                "no_breathing".equalsIgnoreCase(breathing) ||
                "no_pulse".equalsIgnoreCase(circulation) ||
                "severe_bleeding".equalsIgnoreCase(circulation) ||
                "unresponsive".equalsIgnoreCase(disability) ||
                "critical".equalsIgnoreCase(exposure);
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
