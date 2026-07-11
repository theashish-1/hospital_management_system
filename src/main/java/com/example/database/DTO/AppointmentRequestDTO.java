package com.example.database.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class AppointmentRequestDTO {
    private Long doctorId;
    private String doctorName;
    private Long patient_id;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private String reason;
}
