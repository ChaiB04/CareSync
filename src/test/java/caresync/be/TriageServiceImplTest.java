package caresync.be;

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
        int priority = triageService.assignPriorityCombined("blocked", "normal", "stable", "alert", "minor");

        assertEquals(0, priority);
    }

    @Test
    void testImmediateEmergency_NoBreathing() {
        int priority = triageService.assignPriorityCombined("clear", "no_breathing", "stable", "alert", "minor");

        assertEquals(0, priority);
    }

    @Test
    void testImmediateEmergency_NoPulse() {
        int priority = triageService.assignPriorityCombined("clear", "normal", "no_pulse", "alert", "minor");

        assertEquals(0, priority);
    }

    @Test
    void testImmediateEmergency_SevereBleeding() {
        int priority = triageService.assignPriorityCombined("clear", "normal", "severe_bleeding", "alert", "minor");

        assertEquals(0, priority);
    }

    @Test
    void testImmediateEmergency_Unresponsive() {
        int priority = triageService.assignPriorityCombined("clear", "normal", "stable", "unresponsive", "minor");

        assertEquals(0, priority);
    }

    @Test
    void testImmediateEmergency_CriticalExposure() {
        int priority = triageService.assignPriorityCombined("clear", "normal", "stable", "alert", "critical");

        assertEquals(0, priority);
    }

    @Test
    void testU1Priority_PartialAirway() {
        int priority = triageService.assignPriorityCombined("partial", "normal", "normal", "normal", "none");

        assertEquals(1, priority);
    }

    @Test
    void testU1Priority_SevereBreathingDistress() {
        int priority = triageService.assignPriorityCombined("clear", "severe_distress", "normal", "normal", "none");

        assertEquals(1, priority);
    }

    @Test
    void testU1Priority_WeakPulse() {
        int priority = triageService.assignPriorityCombined("clear", "normal", "weak_pulse", "normal", "none");

        assertEquals(1, priority);
    }

    @Test
    void testU1Priority_RespondingToPain() {
        int priority = triageService.assignPriorityCombined("clear", "normal", "normal", "responding_to_pain", "none");

        assertEquals(1, priority);
    }

    @Test
    void testU1Priority_SevereExposure() {
        int priority = triageService.assignPriorityCombined("clear", "normal", "normal", "normal", "severe");

        assertEquals(1, priority);
    }

    @Test
    void testU2Priority_MildBreathingDistress() {
        int priority = triageService.assignPriorityCombined("clear", "mild_distress", "normal", "normal", "none");

        assertEquals(2, priority, "Mild breathing distress should result in priority 2");
    }

    @Test
    void testU2Priority_MildBleeding() {
        int priority = triageService.assignPriorityCombined("clear", "normal", "mild_bleeding", "normal", "none");

        assertEquals(2, priority);
    }

    @Test
    void testU2Priority_ResponsiveToVerbal() {
        int priority = triageService.assignPriorityCombined("clear", "normal", "normal", "responsive_to_verbal", "none");

        assertEquals(2, priority);
    }

    @Test
    void testU2Priority_ModerateExposure() {
        int priority = triageService.assignPriorityCombined("clear", "normal", "normal", "normal", "moderate");

        assertEquals(2, priority);
    }

    @Test
    void testLevel3Priority_StableCirculation() {
        int priority = triageService.assignPriorityCombined("clear", "normal", "stable", "normal", "none");

        assertEquals(3, priority);
    }

    @Test
    void testU3Priority_MildExposure() {

        int priority = triageService.assignPriorityCombined("clear", "normal", "normal", "normal", "mild");

        assertEquals(3, priority);
    }

    @Test
    void testU4Priority_AlertPatient() {

        int priority = triageService.assignPriorityCombined("clear", "normal", "normal", "alert", "none");

        assertEquals(4, priority);
    }

    @Test
    void testU4Priority_MinorExposure() {

        int priority = triageService.assignPriorityCombined("clear", "normal", "normal", "normal", "minor");

        assertEquals(4, priority);
    }

    @Test
    void testUPriority_AllNormal() {

        int priority = triageService.assignPriorityCombined("clear", "normal", "normal", "normal", "none");

        assertEquals(5, priority);
    }

    @Test
    void testCombinedConditions_HighestPriorityWins() {
        int priority = triageService.assignPriorityCombined("partial", "mild_distress", "stable", "alert", "minor");

        assertEquals(1, priority);
    }

}