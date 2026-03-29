package com.example.database.DTO;

import com.example.database.Entity.type.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginResponseDTO {

    private Long userId;
    private String Username;
    private Role Role;
    private String token;
    private Long patientId;
    private Long doctorId;
}
