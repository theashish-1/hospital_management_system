package com.example.database.DTO;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class DoctorViewResponseDTO {
    private String name;
    private String specialization;
    private int experience;
    private String email;
    private BigDecimal consultationFee; 
}
