package com.example.database.DTO;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class DoctorProfileUpdateDTO {
    private String specialization;
    private int age;
    private int experience;
    private String phone;
    private String email;
    private BigDecimal consultationFee;
    private List<String> availableDays;
}
