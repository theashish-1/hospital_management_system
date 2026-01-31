package com.example.database.Service;

import com.example.database.Entity.Patient;
import com.example.database.Repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
