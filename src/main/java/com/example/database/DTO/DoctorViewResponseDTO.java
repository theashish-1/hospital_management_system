package com.example.database.DTO;

import java.math.BigDecimal;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class DoctorViewResponseDTO {
    private Long doctorId;
    private String name;
    private String specialization;
    private int experience;
    private String email;
    private BigDecimal consultationFee; 
}
