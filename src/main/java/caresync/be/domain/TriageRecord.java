package caresync.be.domain;

import lombok.Data;

import java.util.Date;

@Data
public class TriageRecord {
    int id;
    Date date;
    String complaint;
    String initialAssessment;
    ABCDEAssessment abcdeAssessment;
    int urgencyFactor;
}
