package caresync.be;

import caresync.be.domain.ABCDEAssessment;
import caresync.be.domain.TriageRecord;
import caresync.be.service.impl.TriageServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TriageServiceImplTest {

    @InjectMocks
    private TriageServiceImpl triageService;

    @Test
    void testImmediateEmergency_BlockedAirway() {

        ABCDEAssessment abcdeAssessment = ABCDEAssessment.builder()
                .airway("blocked")
                .breathing("no_breathing")
                .circulation("mild")
                .disability("stable")
                .exposure("minor").build();

        TriageRecord triageRecord = TriageRecord.builder()
                .complaint("Choking")
                .abcdeAssessment(abcdeAssessment)
                .initialAssessment(5)
                .build();
        int priority = triageService.assignPriorityCombined(triageRecord);

        assertEquals(0, priority);
    }

    @Test
    void testImmediateEmergency_NoBreathingAndNoPulse() {

        ABCDEAssessment abcdeAssessment = ABCDEAssessment.builder()
                .airway("clear")
                .breathing("no_breathing")
                .circulation("no_pulse")
                .disability("unresponsive")
                .exposure("minor").build();

        TriageRecord triageRecord = TriageRecord.builder()
                .complaint("Unconscious")
                .abcdeAssessment(abcdeAssessment)
                .initialAssessment(0)
                .build();
        int priority = triageService.assignPriorityCombined(triageRecord);

        assertEquals(0, priority);
    }

    @Test
    void testImmediateEmergency_SevereBleeding() {
        ABCDEAssessment abcdeAssessment = ABCDEAssessment.builder()
                .airway("clear")
                .breathing("normal")
                .circulation("severe_bleeding")
                .disability("alert")
                .exposure("minor").build();

        TriageRecord triageRecord = TriageRecord.builder()
                .complaint("Stabbed in the chest")
                .abcdeAssessment(abcdeAssessment)
                .initialAssessment(0)
                .build();

        int priority = triageService.assignPriorityCombined(triageRecord);

        assertEquals(0, priority);
    }

    @Test
    void testImmediateEmergency_Unresponsive() {
        ABCDEAssessment abcdeAssessment = ABCDEAssessment.builder()
                .airway("clear")
                .breathing("normal")
                .circulation("stable")
                .disability("unresponsive")
                .exposure("minor").build();

        TriageRecord triageRecord = TriageRecord.builder()
                .complaint("Does not wake up")
                .abcdeAssessment(abcdeAssessment)
                .initialAssessment(0)
                .build();

        int priority = triageService.assignPriorityCombined(triageRecord);

        assertEquals(0, priority);
    }

    @Test
    void testImmediateEmergency_CriticalExposure() {
        ABCDEAssessment abcdeAssessment = ABCDEAssessment.builder()
                .airway("clear")
                .breathing("normal")
                .circulation("weak_pulse")
                .disability("alert")
                .exposure("critical").build();

        TriageRecord triageRecord = TriageRecord.builder()
                .complaint("Internal bleeding?")
                .abcdeAssessment(abcdeAssessment)
                .initialAssessment(0)
                .build();


        int priority = triageService.assignPriorityCombined(triageRecord);

        assertEquals(0, priority);
    }

    @Test
    void testU1Priority_PartialAirway() {
        ABCDEAssessment abcdeAssessment = ABCDEAssessment.builder()
                .airway("partial")
                .breathing("normal")
                .circulation("normal")
                .disability("normal")
                .exposure("none").build();

        TriageRecord triageRecord = TriageRecord.builder()
                .complaint("choking/asthma")
                .abcdeAssessment(abcdeAssessment)
                .initialAssessment(0)
                .build();

        int priority = triageService.assignPriorityCombined(triageRecord);

        assertEquals(1, priority);
    }

    @Test
    void testU1Priority_SevereBreathingDistress() {
        ABCDEAssessment abcdeAssessment = ABCDEAssessment.builder()
                .airway("partial")
                .breathing("severe_distress")
                .circulation("normal")
                .disability("normal")
                .exposure("none").build();

        TriageRecord triageRecord = TriageRecord.builder()
                .complaint("Panic attack")
                .abcdeAssessment(abcdeAssessment)
                .initialAssessment(0)
                .build();

        int priority = triageService.assignPriorityCombined(triageRecord);

        assertEquals(1, priority);
    }

    @Test
    void testU1Priority_WeakPulse() {
        ABCDEAssessment abcdeAssessment = ABCDEAssessment.builder()
                .airway("clear")
                .breathing("severe_distress")
                .circulation("weak_pulse")
                .disability("normal")
                .exposure("none").build();

        TriageRecord triageRecord = TriageRecord.builder()
                .complaint("low heartrate")
                .abcdeAssessment(abcdeAssessment)
                .initialAssessment(0)
                .build();

        int priority = triageService.assignPriorityCombined(triageRecord);

        assertEquals(1, priority);
    }

    @Test
    void testU1Priority_RespondingToPain() {
        ABCDEAssessment abcdeAssessment = ABCDEAssessment.builder()
                .airway("clear")
                .breathing("normal")
                .circulation("normal")
                .disability("responding_to_pain")
                .exposure("none").build();

        TriageRecord triageRecord = TriageRecord.builder()
                .complaint("Internal wound")
                .abcdeAssessment(abcdeAssessment)
                .initialAssessment(0)
                .build();

        int priority = triageService.assignPriorityCombined(triageRecord);

        assertEquals(1, priority);
    }

    @Test
    void testU1Priority_SevereExposure() {
        ABCDEAssessment abcdeAssessment = ABCDEAssessment.builder()
                .airway("clear")
                .breathing("normal")
                .circulation("normal")
                .disability("normal")
                .exposure("severe").build();

        TriageRecord triageRecord = TriageRecord.builder()
                .complaint("allergic reaction, panic attack")
                .abcdeAssessment(abcdeAssessment)
                .initialAssessment(0)
                .build();

        int priority = triageService.assignPriorityCombined(triageRecord);

        assertEquals(1, priority);
    }

    @Test
    void testU2Priority_MildBreathingDistress() {
        ABCDEAssessment abcdeAssessment = ABCDEAssessment.builder()
                .airway("clear")
                .breathing("mild_distress")
                .circulation("normal")
                .disability("normal")
                .exposure("none").build();

        TriageRecord triageRecord = TriageRecord.builder()
                .complaint("panic, heart rate increased")
                .abcdeAssessment(abcdeAssessment)
                .initialAssessment(3)
                .build();
        int priority = triageService.assignPriorityCombined(triageRecord);

        assertEquals(2, priority, "Mild breathing distress should result in priority 2");
    }

    @Test
    void testU2Priority_MildBleeding() {
        ABCDEAssessment abcdeAssessment = ABCDEAssessment.builder()
                .airway("clear")
                .breathing("normal")
                .circulation("mild_bleeding")
                .disability("normal")
                .exposure("none").build();

        TriageRecord triageRecord = TriageRecord.builder()
                .complaint("cut by knife")
                .abcdeAssessment(abcdeAssessment)
                .initialAssessment(3)
                .build();

        int priority = triageService.assignPriorityCombined(triageRecord);

        assertEquals(2, priority);
    }

    @Test
    void testU2Priority_ResponsiveToVerbal() {
        ABCDEAssessment abcdeAssessment = ABCDEAssessment.builder()
                .airway("clear")
                .breathing("normal")
                .circulation("normal")
                .disability("responsive_to_verbal")
                .exposure("none").build();

        TriageRecord triageRecord = TriageRecord.builder()
                .complaint("cannot move")
                .abcdeAssessment(abcdeAssessment)
                .initialAssessment(3)
                .build();

        int priority = triageService.assignPriorityCombined(triageRecord);

        assertEquals(2, priority);
    }

    @Test
    void testU2Priority_ModerateExposure() {
        ABCDEAssessment abcdeAssessment = ABCDEAssessment.builder()
                .airway("clear")
                .breathing("normal")
                .circulation("normal")
                .disability("normal")
                .exposure("moderate").build();

        TriageRecord triageRecord = TriageRecord.builder()
                .complaint("very pale")
                .abcdeAssessment(abcdeAssessment)
                .initialAssessment(3)
                .build();

        int priority = triageService.assignPriorityCombined(triageRecord);

        assertEquals(2, priority);
    }

    @Test
    void testLevel3Priority_StableCirculation() {
        ABCDEAssessment abcdeAssessment = ABCDEAssessment.builder()
                .airway("clear")
                .breathing("normal")
                .circulation("stable")
                .disability("normal")
                .exposure("none").build();

        TriageRecord triageRecord = TriageRecord.builder()
                .complaint("bleeding but stable")
                .abcdeAssessment(abcdeAssessment)
                .initialAssessment(3)
                .build();

        int priority = triageService.assignPriorityCombined(triageRecord);

        assertEquals(3, priority);
    }

    @Test
    void testU3Priority_MildExposure() {
        ABCDEAssessment abcdeAssessment = ABCDEAssessment.builder()
                .airway("clear")
                .breathing("normal")
                .circulation("stable")
                .disability("normal")
                .exposure("mild").build();

        TriageRecord triageRecord = TriageRecord.builder()
                .complaint("pale")
                .abcdeAssessment(abcdeAssessment)
                .initialAssessment(3)
                .build();

        int priority = triageService.assignPriorityCombined(triageRecord);

        assertEquals(3, priority);
    }

    @Test
    void testU4Priority_AlertPatient() {
        ABCDEAssessment abcdeAssessment = ABCDEAssessment.builder()
                .airway("clear")
                .breathing("normal")
                .circulation("normal")
                .disability("alert")
                .exposure("none").build();

        TriageRecord triageRecord = TriageRecord.builder()
                .complaint("panic")
                .abcdeAssessment(abcdeAssessment)
                .initialAssessment(3)
                .build();

        int priority = triageService.assignPriorityCombined(triageRecord);

        assertEquals(4, priority);
    }

    @Test
    void testU4Priority_MinorExposure() {
        ABCDEAssessment abcdeAssessment = ABCDEAssessment.builder()
                .airway("clear")
                .breathing("normal")
                .circulation("normal")
                .disability("normal")
                .exposure("minor").build();

        TriageRecord triageRecord = TriageRecord.builder()
                .complaint("blue spots")
                .abcdeAssessment(abcdeAssessment)
                .initialAssessment(3)
                .build();

        int priority = triageService.assignPriorityCombined(triageRecord);

        assertEquals(4, priority);
    }

    @Test
    void testUPriority_AllNormal() {
        ABCDEAssessment abcdeAssessment = ABCDEAssessment.builder()
                .airway("clear")
                .breathing("normal")
                .circulation("normal")
                .disability("normal")
                .exposure("none").build();

        TriageRecord triageRecord = TriageRecord.builder()
                .complaint("pain in leg")
                .abcdeAssessment(abcdeAssessment)
                .initialAssessment(3)
                .build();

        int priority = triageService.assignPriorityCombined(triageRecord);

        assertEquals(5, priority);
    }

    @Test
    void testCombinedConditions_HighestPriorityWins() {
        ABCDEAssessment abcdeAssessment = ABCDEAssessment.builder()
                .airway("partial")
                .breathing("mild_distress")
                .circulation("stable")
                .disability("alert")
                .exposure("minor").build();

        TriageRecord triageRecord = TriageRecord.builder()
                .complaint("noises while breathing")
                .abcdeAssessment(abcdeAssessment)
                .initialAssessment(3)
                .build();
        int priority = triageService.assignPriorityCombined(triageRecord);

        assertEquals(1, priority);
    }

}