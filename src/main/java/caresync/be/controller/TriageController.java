package caresync.be.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/triage")
@AllArgsConstructor
public class TriageController {

    @PostMapping()
    public String getUsers(@RequestBody String body) {
        try{
            return "yea";
        }
        catch (Exception e){

        }
        return "error";
    }
}
