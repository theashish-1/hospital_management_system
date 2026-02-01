package com.example.database.Controller;

import com.example.database.Entity.Patient;
import com.example.database.Service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Patient>> getById(@PathVariable Long id){
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @PutMapping("/updatePatient/{id}")
    public ResponseEntity<Patient> updatePatient(@RequestBody Patient patient,@PathVariable Long id){
        return ResponseEntity.ok(patientService.updatePatient(patient,id));
    }
    @DeleteMapping("/deletePatient/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.ok("Patient deleted successfully");
    }

}
