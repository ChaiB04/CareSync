package caresync.be.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ABCDEAssessment {
   String airway;
   String breathing;
   String circulation;
   String disability;
   String exposure;
}
