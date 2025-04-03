package caresync.be.controller.converter;

import caresync.be.controller.dto.TriageRequest;
import caresync.be.domain.TriageRecord;

public class TriageRecordConverter {

    public static TriageRecord triageRequestToTriageRecord(TriageRequest request) {
        return TriageRecord.builder()
                .initialAssessment(request.getInitialAssessment())
                .complaint(request.getComplaint())
                .abcdeAssessment(request.getAbcdeAssessment())
                .build();
    }
}
