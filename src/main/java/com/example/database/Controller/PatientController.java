package com.example.database.Controller;

import com.example.database.Entity.Patient;
import com.example.database.Service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/patient")
public class PatientController {
    private final PatientService patientService;
    public PatientController(PatientService patientService){
        this.patientService = patientService;
    }

    @PostMapping("/addPatient")
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient){
        return ResponseEntity.ok(patientService.addPatient(patient));
    }
    @GetMapping("/patients")
    public ResponseEntity<List<Patient>> getPatient(){
        return ResponseEntity.ok(patientService.getPatient());
    }
}
