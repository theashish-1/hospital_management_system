package com.example.database.Controller;

import com.example.database.DTO.AppointmentResponseDTO;
import com.example.database.DTO.AppointmentRequestDTO;
import com.example.database.Service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService){
        this.appointmentService = appointmentService;
    }
    @PostMapping("bookAppointment")
    public ResponseEntity<AppointmentResponseDTO> bookAppointment(@RequestBody AppointmentRequestDTO appointment){
        return ResponseEntity.ok(appointmentService.bookAppointmentPatient(appointment));

    }
    @PutMapping("/cancelAppointment/{id}")
    public ResponseEntity<String> cancelAppointment(@PathVariable Long id){
        appointmentService.cancelAppointment(id);
        return ResponseEntity.ok("Appointment deleted ");
    }

}
