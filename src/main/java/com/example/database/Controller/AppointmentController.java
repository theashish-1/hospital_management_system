package com.example.database.Controller;

import com.example.database.DTO.AppointmentResponseDTO;
import com.example.database.DTO.AppointmentRequestDTO;
import com.example.database.Service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
