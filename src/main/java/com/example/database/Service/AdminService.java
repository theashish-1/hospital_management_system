package com.example.database.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.database.DTO.AdminStatsSummaryDTO;
import com.example.database.Entity.Patient;
import com.example.database.Repository.AppointmentRepository;
import com.example.database.Repository.DoctorRepository;
import com.example.database.Repository.PatientRepository;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminService {
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;

    public AdminStatsSummaryDTO hospitalSummary() {
        //to return total count of doctor , patient and today's appointment list 
        long doctorCount = doctorRepository.count();
        long patientCount = patientRepository.count();
        log.info("doctor count is"+doctorCount);
        log.info("patinent count is "+patientCount);

        long todayAppointmentCount = appointmentRepository.countByAppointmentDate(java.time.LocalDate.now());
        log.info("today appointment count is "+todayAppointmentCount);
        

        return AdminStatsSummaryDTO.builder()
                .doctorCount(doctorCount)
                .patientCount(patientCount)
                .todayAppointmentCount(todayAppointmentCount)
                .build();
    


    }

    
}