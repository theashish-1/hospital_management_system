package com.example.database.Controller;

import com.example.database.DTO.PatientEntryDTO;
<<<<<<< HEAD
import com.example.database.DTO.PatientProfileResponse;
import com.example.database.DTO.PatientProfileUpdateDTO;
=======
>>>>>>> 46cbaac4155ca17de5d9faae764d05bf320feb38
import com.example.database.DTO.PatientResponseDTO;
import com.example.database.Entity.Patient;
import com.example.database.Service.PatientService;
import org.springframework.http.ResponseEntity;
<<<<<<< HEAD
import org.springframework.security.access.prepost.PreAuthorize;
=======
>>>>>>> 46cbaac4155ca17de5d9faae764d05bf320feb38
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

<<<<<<< HEAD
    @PreAuthorize("hasAnyRole('ADMIN' , 'PATIENT')")
=======
>>>>>>> 46cbaac4155ca17de5d9faae764d05bf320feb38
    @PostMapping("/addPatient")
    public ResponseEntity<PatientResponseDTO> addPatient(@RequestBody PatientEntryDTO patientEntryDTO){
        return ResponseEntity.ok(patientService.addPatient(patientEntryDTO));
    }
<<<<<<< HEAD
    @PreAuthorize("hasRole('ADMIN')")
=======
>>>>>>> 46cbaac4155ca17de5d9faae764d05bf320feb38
    @GetMapping("/patients")
    public ResponseEntity<List<PatientResponseDTO>> getPatient(){
        return ResponseEntity.ok(patientService.getPatient());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(patientService.getPatientById(id));
    }
<<<<<<< HEAD
    @PreAuthorize("hasRole('ADMIN')")
=======

>>>>>>> 46cbaac4155ca17de5d9faae764d05bf320feb38
    @PutMapping("/updatePatient/{id}")
    public ResponseEntity<Patient> updatePatient(@RequestBody Patient patient,@PathVariable Long id){
        return ResponseEntity.ok(patientService.updatePatient(patient,id));
    }
<<<<<<< HEAD
    @PreAuthorize("hasRole('ADMIN')")
=======
>>>>>>> 46cbaac4155ca17de5d9faae764d05bf320feb38
    @DeleteMapping("/deletePatient/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.ok("Patient deleted successfully");
    }
<<<<<<< HEAD
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/updatePatinet")
    public ResponseEntity<PatientProfileResponse> updatePatientProfile(@PathVariable Long id, @RequestBody PatientProfileUpdateDTO patientProfileUpdateDTO){
        return ResponseEntity.ok(patientService.updatePatientProfile(patientProfileUpdateDTO,id));
    }


=======
>>>>>>> 46cbaac4155ca17de5d9faae764d05bf320feb38

}
