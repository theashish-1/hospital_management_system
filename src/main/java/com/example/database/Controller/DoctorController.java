package com.example.database.Controller;

import com.example.database.DTO.DoctorProfileResponseDTO;
import com.example.database.DTO.DoctorProfileUpdateDTO;
import com.example.database.DTO.DoctorRequestDTO;
import com.example.database.DTO.DoctorResponseDTO;
import com.example.database.DTO.DoctorViewResponseDTO;
import com.example.database.Entity.Doctor;
import com.example.database.Service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/v1/doctor")
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService){
        this.doctorService = doctorService;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addDoctor")
    public ResponseEntity<DoctorResponseDTO> addDoctor(@RequestBody DoctorRequestDTO doctorRequestDTO){
        return ResponseEntity.ok(doctorService.addDoctor(doctorRequestDTO));
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'PATIENT', 'DOCTOR')")
    @GetMapping("/doctorList")
    public ResponseEntity<List<DoctorViewResponseDTO>> getDoctor(){
        return ResponseEntity.ok(doctorService.getDoctors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Doctor>> getSpecificDoctor(@PathVariable Long id){
        return ResponseEntity.ok(doctorService.getDoctorByid(id));
    }
    @PreAuthorize("hasRole('ADMIN')")   
    @PutMapping("/updateDoctor/{id}")
    public ResponseEntity<Doctor> updateDoctor(@RequestBody Doctor doctor,@PathVariable Long id){
        return ResponseEntity.ok(doctorService.updateDoctor(doctor,id));
    }
    @DeleteMapping("deleteDoctor/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable Long id){
        doctorService.deleteDoctor(id);
        return ResponseEntity.ok( "Doctor deleted successfully with id : "+id);

    }
    @GetMapping("/specialization/{specialization}")
    public ResponseEntity<List<Doctor>> getBySpecialization(@PathVariable String specialization) {
        return ResponseEntity.ok(doctorService.getDoctorsBySpecialization(specialization));
    }
    @GetMapping("/experience/{year}")
    public ResponseEntity<List<Doctor>> getDoctorByYearOfExperience(@PathVariable int year){
        return ResponseEntity.ok(doctorService.getDoctorByYearOfExperience(year));
    }
    @PreAuthorize("hasRole('DOCTOR') and #id == authentication.principal.id")
    @PutMapping("/{id}/updateProfile")
    public ResponseEntity<DoctorProfileResponseDTO> updateDoctorProfile(@PathVariable Long id, @RequestBody DoctorProfileUpdateDTO doctorProfileUpdateDTO){
        DoctorProfileResponseDTO profileResponseDTO = doctorService.updateDoctorProfile(id,doctorProfileUpdateDTO);
        return ResponseEntity.ok(profileResponseDTO);
    }



}
