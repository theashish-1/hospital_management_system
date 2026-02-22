package com.example.database.Repository;

import com.example.database.DTO.AppointmentRequestDTO;
import com.example.database.Entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
}
