package caresync.be.controller.dto;

import caresync.be.domain.ABCDEAssessment;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TriageRequest {
    int initialAssessment;
    String complaint;
    ABCDEAssessment abcdeAssessment;
}
