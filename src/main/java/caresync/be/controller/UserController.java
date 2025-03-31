package caresync.be.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {


    @GetMapping()
    public String getUsers() {
       try{
           return "users";
       }
       catch (Exception e){

       }
       return "error";
    }
}
