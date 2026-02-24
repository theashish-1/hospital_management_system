package com.example.database.Repository;

import com.example.database.DTO.AppointmentRequestDTO;
import com.example.database.Entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    boolean existsByDoctorIdAndAppointmentDateAndAppointmentTime(Long doctorId , LocalDate appointmentDate , LocalTime appointmentTime);
}
