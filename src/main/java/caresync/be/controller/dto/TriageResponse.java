package caresync.be.controller.dto;

import caresync.be.domain.TriageRecord;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class TriageResponse {
     TriageRecord triageRecord;
}
