package com.example.database.DTO;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.EmbeddedTable;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class DoctorProfileResponseDTO {
    private Long id;
    private String name;
    private String specialization;
    private int age;
    private int experience;
    private String phone;
    private String email;
    private BigDecimal consultationFee;
    private List<String> availableDays;
}
