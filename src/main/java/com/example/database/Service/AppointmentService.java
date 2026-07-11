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
import com.nimbusds.openid.connect.sdk.rp.ApplicationType;

import java.util.List;
import java.util.stream.Collectors;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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
        Doctor doctor = doctorRepository.findById(appointment.getDoctorId())
                .orElseThrow( ()-> new RuntimeException("doctor not found"));
        //below sql query is StatusNot hence if appointment status is cancelled isAppointmentPresent will be true
        boolean isAppointmentPresent = appointmentRepository.existsByDoctorIdAndAppointmentDateAndAppointmentTimeAndStatusNot(doctor.getId(),
                appointment.getAppointmentDate(),
                appointment.getAppointmentTime(),
                AppointmentStatus.CANCELLED);
        if (isAppointmentPresent){
            throw new RuntimeException("appointment already booked ");
        }
        String selectedDay = appointment.getAppointmentDate().getDayOfWeek().name().toLowerCase();
        boolean doctorAvailableDay = doctor.getAvailableDays().stream()
                .anyMatch(day -> day.equalsIgnoreCase(selectedDay));
        if(doctorAvailableDay == false){
            throw new RuntimeException("Doctor not available on "+selectedDay);
        }

        Appointment appointment1 = new Appointment();
        appointment1.setPatient(patient);
        appointment1.setDoctor(doctor);
        appointment1.setAppointmentTime(appointment.getAppointmentTime());
        appointment1.setAppointmentDate(appointment.getAppointmentDate());
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


    public String confirmAppointment(Long id) {
        String currentDoctor = SecurityContextHolder.getContext().getAuthentication().getName();
        Appointment currAppointment =  appointmentRepository.findById(id)
                                        .orElseThrow( ()-> new RuntimeException("Appointment not found"));
        
        //check if the comming appointment belongs to the current doctor
        if (!currAppointment.getDoctor().getUser().getUsername().equals(currentDoctor)) {
            throw new RuntimeException("This appointment is not yours !! ");
        }
        currAppointment.setStatus(AppointmentStatus.CONFIRMED);
        appointmentRepository.save(currAppointment);
        return "Appointment confirmed";
        
    }


    public List<AppointmentResponseDTO> getDoctorsAppointments() {
        
       String username = SecurityContextHolder.getContext().getAuthentication().getName();
       List<Appointment> appointments = appointmentRepository.findByDoctorUserUsername(username);

       return appointments.stream()
               .map(appointment -> {
                AppointmentResponseDTO responseDTO = new AppointmentResponseDTO();
                responseDTO.setId(appointment.getId());
                responseDTO.setPatientName(appointment.getPatient().getName());
                responseDTO.setDoctorId(appointment.getDoctor().getId());
                responseDTO.setDoctorName(appointment.getDoctor().getName());
                responseDTO.setAppointmentDate(appointment.getAppointmentDate());
                responseDTO.setAppointmentTime(appointment.getAppointmentTime()); 
                responseDTO.setStatus(appointment.getStatus().name());
                responseDTO.setReason(appointment.getReason());
                return responseDTO;
               })
               .collect(Collectors.toList());
       

    }
    public String rejectAppointment(Long id) {
       String currDoctor = SecurityContextHolder.getContext().getAuthentication().getName();
       Appointment curAppointment = appointmentRepository.findById(id)
                                    .orElseThrow(()-> new RuntimeException("Appointment not found"));
        if(!curAppointment.getDoctor().getUser().getUsername().equals(currDoctor)){
            throw new RuntimeException("This appointment is not yours");

        }
        curAppointment.setStatus(AppointmentStatus.CANCELLED);
        appointmentRepository.save(curAppointment);
        return "Appointment rejected";
    }
}
