package caresync.be.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class TriageRecord {
    int id;
    Date date;
    String complaint;
    int initialAssessment;
    ABCDEAssessment abcdeAssessment;
    int urgencyFactor;
}
