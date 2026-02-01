package com.example.database.Service;

import com.example.database.Entity.Patient;
import com.example.database.Repository.PatientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    public PatientService(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }
    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public List<Patient> getPatient() {
        return patientRepository.findAll();
    }

    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    public Patient updatePatient(Patient patient, Long id) {
        //check if existing patient is present
        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow( ()-> new RuntimeException("Patient not found with Id "+id));



        existingPatient.setName(patient.getName());
        existingPatient.setAge(patient.getAge());
        existingPatient.setGender(patient.getGender());
        return patientRepository.save(existingPatient);



    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}
