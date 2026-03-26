package com.example.database.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data

@AllArgsConstructor
@NoArgsConstructor
public class SignupResponseDTO {
    private Long Id;
    private String username;
    private String role;
    private Long patientId;
    private Long doctorId;



}
