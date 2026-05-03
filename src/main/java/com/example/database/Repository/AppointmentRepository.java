package com.example.database.Repository;

import com.example.database.DTO.AppointmentRequestDTO;
import com.example.database.Entity.Appointment;
import com.example.database.Entity.type.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
<<<<<<< HEAD
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    boolean existsByDoctorIdAndAppointmentDateAndAppointmentTimeAndStatusNot(Long doctorId , LocalDate appointmentDate , LocalTime appointmentTime , AppointmentStatus status);
    long countByAppointmentDate(LocalDate appointmentDate);

    List<Appointment> findByDoctorUserUsername(String name);
=======

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    boolean existsByDoctorIdAndAppointmentDateAndAppointmentTimeAndStatusNot(Long doctorId , LocalDate appointmentDate , LocalTime appointmentTime , AppointmentStatus status);
>>>>>>> 46cbaac4155ca17de5d9faae764d05bf320feb38
}
