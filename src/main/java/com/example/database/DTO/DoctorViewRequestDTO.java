package com.example.database.DTO;

import lombok.Data;

@Data
public class DoctorViewRequestDTO {
    private String name;
    private String specialization;
    private int experience;
    private String phone;
    private String email;
    private String consultationFee; 
}
