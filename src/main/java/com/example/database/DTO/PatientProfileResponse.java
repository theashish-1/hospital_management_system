package com.example.database.DTO;

import com.example.database.Entity.type.BloodGroupType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
public class PatientProfileResponse {
    private String registrationDate;
    private String disease_history;
    private String address;
    private String gender;
    private Long age;
    private String phone;
    private BloodGroupType bloodGroupType;
    private String email;
}
