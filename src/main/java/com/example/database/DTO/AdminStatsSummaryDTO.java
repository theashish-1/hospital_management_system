package com.example.database.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminStatsSummaryDTO {
    private long doctorCount;
    private long patientCount;
    private long todayAppointmentCount;


}
