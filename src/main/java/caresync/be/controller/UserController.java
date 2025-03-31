package caresync.be.controller;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final Logger logger = LogManager.getLogger(UserController.class);

    @GetMapping()
    public String getUsers() {
       try{
           logger.info("getUsers");
           return "users";
       }
       catch (Exception e){
           logger.error(e);
       }
       logger.warn("outside of try-catch");
       return "error";
    }
}
