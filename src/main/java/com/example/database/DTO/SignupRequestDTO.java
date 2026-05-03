package com.example.database.DTO;

import com.example.database.Entity.type.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

<<<<<<< HEAD
import java.util.Set;

=======
>>>>>>> 46cbaac4155ca17de5d9faae764d05bf320feb38
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequestDTO {
    private String username;
    private String password;
<<<<<<< HEAD
    private Set<Role> roles;
    private String name;
=======
    private Role role;
>>>>>>> 46cbaac4155ca17de5d9faae764d05bf320feb38

}
