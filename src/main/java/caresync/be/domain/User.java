package caresync.be.domain;

import lombok.Data;

@Data
public class User {
    int employeeId;
    String firstName;
    String lastName;
    String email;
    String password;
}
