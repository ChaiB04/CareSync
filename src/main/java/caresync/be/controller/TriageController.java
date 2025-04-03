package caresync.be.controller;

import caresync.be.controller.dto.TriageRequest;
import caresync.be.controller.dto.TriageResponse;
import caresync.be.service.TriageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/triage")
@AllArgsConstructor
public class TriageController {

    private TriageService triageService;

//    @PostMapping()
//    public ResponseEntity<TriageResponse> getUsers(@RequestBody TriageRequest request) {
//        try{
//            return ResponseEntity.ok().build();
//        }
//        catch (Exception e){
//            return ResponseEntity.badRequest().build();
//        }
//    }

    @PostMapping()
    public String assessTriage(@RequestBody TriageRequest request) {

        int priority = triageService.assignPriorityCombined(request.getAbcdeAssessment().getAirway(),
                request.getAbcdeAssessment().getBreathing(),
                request.getAbcdeAssessment().getCirculation(),
                request.getAbcdeAssessment().getDisability(),
                request.getAbcdeAssessment().getExposure());
        return "Patient Priority Level: " + priority + " \n visual assessment: " + request.getInitialAssessment();

    }
}
