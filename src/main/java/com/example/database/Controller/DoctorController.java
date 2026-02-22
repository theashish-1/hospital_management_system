package com.example.database.Controller;

import com.example.database.Entity.Doctor;
import com.example.database.Service.DoctorService;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/addDoctor")
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor){
        return ResponseEntity.ok(doctorService.addDoctor(doctor));
    }
    @GetMapping("/doctorList")
    public ResponseEntity<List<Doctor>> getDoctor(){
        return ResponseEntity.ok(doctorService.getDoctors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Doctor>> getSpecificDoctor(@PathVariable Long id){
        return ResponseEntity.ok(doctorService.getDoctorByid(id));
    }
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



}
