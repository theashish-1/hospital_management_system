package com.example.database.DTO;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class DoctorResponseDTO {
    private int doctorId;
    private String name;
    private String specialization;
    private int age;
    private int experience;
    private String phone;
    private String email;
    private BigDecimal consultationFee;
    

}
