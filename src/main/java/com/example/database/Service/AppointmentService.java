package com.example.database.Service;

import com.example.database.DTO.AppointmentResponseDTO;
import com.example.database.DTO.AppointmentRequestDTO;
import com.example.database.Entity.Appointment;
import com.example.database.Entity.Doctor;
import com.example.database.Entity.Patient;
import com.example.database.Entity.type.AppointmentStatus;
import com.example.database.Repository.AppointmentRepository;
import com.example.database.Repository.DoctorRepository;
import com.example.database.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    public AppointmentResponseDTO bookAppointmentPatient(AppointmentRequestDTO appointment) {
        Patient patient = patientRepository.findById(appointment.getPatient_id())

                .orElseThrow( ()-> new RuntimeException("patient not fount"));
        Doctor doctor = doctorRepository.findById(appointment.getDoctor_id())
                .orElseThrow( ()-> new RuntimeException("doctor not found"));
        //below sql query is StatusNot hence if appointment status is cancelled isAppointmentPresent will be true
        boolean isAppointmentPresent = appointmentRepository.existsByDoctorIdAndAppointmentDateAndAppointmentTimeAndStatusNot(doctor.getId(),
                appointment.getDate(),
                appointment.getTime(),
                AppointmentStatus.CANCELLED);
        if (isAppointmentPresent){
            throw new RuntimeException("appointment already booked ");
        }
        String selectedDay = appointment.getDate().getDayOfWeek().name();
        boolean doctorAvailableDay = doctor.getAvailableDays().contains(selectedDay);
        if(doctorAvailableDay == false){
            throw new RuntimeException("Doctor not available on "+selectedDay);
        }

        Appointment appointment1 = new Appointment();
        appointment1.setPatient(patient);
        appointment1.setDoctor(doctor);
        appointment1.setAppointmentTime(appointment.getTime());
        appointment1.setAppointmentDate(appointment.getDate());
        appointment1.setReason(appointment.getReason());
        appointment1.setStatus(AppointmentStatus.BOOKED);
        Appointment saved =  appointmentRepository.save(appointment1);

        AppointmentResponseDTO response = new AppointmentResponseDTO();
        response.setId(saved.getId());
        response.setPatientId(patient.getId());
        response.setPatientName(patient.getName());
        response.setDoctorId(doctor.getId());
        response.setDoctorName(doctor.getName());
        response.setAppointmentDate(saved.getAppointmentDate());
        response.setAppointmentTime(saved.getAppointmentTime());
        response.setStatus(saved.getStatus().name());
        response.setReason(saved.getReason());
        return response;
    }


    public void cancelAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow( ()-> new RuntimeException("Appointment not found"));
        appointment.setStatus(AppointmentStatus.CANCELLED);
        appointmentRepository.save(appointment);

    }
}
