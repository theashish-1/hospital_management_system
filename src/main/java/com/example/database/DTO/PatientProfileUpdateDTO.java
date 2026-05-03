package com.example.database.DTO;

import com.example.database.Entity.type.BloodGroupType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@AllArgsConstructor
@Data
public class PatientProfileUpdateDTO {
    private LocalDate registrationDate;
    private String disease_history;
    private String address;
    private String gender;
    private Long age;
    private String phone;
    private BloodGroupType bloodGroupType;
    private String email;

}
