package com.example.database.Controller;

import com.example.database.DTO.AppointmentResponseDTO;
import com.example.database.DTO.AppointmentRequestDTO;
import com.example.database.Service.AppointmentService;
<<<<<<< HEAD

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/v1/appointments")
=======
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
>>>>>>> 46cbaac4155ca17de5d9faae764d05bf320feb38
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService){
        this.appointmentService = appointmentService;
    }
<<<<<<< HEAD
    @PreAuthorize("hasAnyRole( 'ADMIN' , 'PATIENT')")
=======
>>>>>>> 46cbaac4155ca17de5d9faae764d05bf320feb38
    @PostMapping("bookAppointment")
    public ResponseEntity<AppointmentResponseDTO> bookAppointment(@RequestBody AppointmentRequestDTO appointment){
        return ResponseEntity.ok(appointmentService.bookAppointmentPatient(appointment));

    }
<<<<<<< HEAD
    @PreAuthorize("hasAnyRole( 'ADMIN' , 'PATIENT')")
=======
>>>>>>> 46cbaac4155ca17de5d9faae764d05bf320feb38
    @PutMapping("/cancelAppointment/{id}")
    public ResponseEntity<String> cancelAppointment(@PathVariable Long id){
        appointmentService.cancelAppointment(id);
        return ResponseEntity.ok("Appointment deleted ");
    }
<<<<<<< HEAD
    @PreAuthorize("hasRole('DOCTOR')")
    @PutMapping("/{id}/confirmAppointment")
    public String confirmAppointment(@PathVariable Long id) {

        String response = appointmentService.confirmAppointment(id);
        return response;
    }
    @PreAuthorize("hasRole('DOCTOR')")
    @GetMapping("/viewAppointments")
    public ResponseEntity<List<AppointmentResponseDTO>> getDoctorsAppointments() {
        return ResponseEntity.ok(appointmentService.getDoctorsAppointments());
    }
    @PreAuthorize("hasRole('DOCTOR')")
    @PutMapping("/reject/{id}")
    public String rejectAppointment(@PathVariable Long id) {
        String response = appointmentService.rejectAppointment(id);
        return response;
    }
    
    
=======
>>>>>>> 46cbaac4155ca17de5d9faae764d05bf320feb38

}
