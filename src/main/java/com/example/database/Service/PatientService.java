package com.example.database.Service;

import com.example.database.DTO.PatientEntryDTO;
import com.example.database.DTO.PatientResponseDTO;
import com.example.database.Entity.Patient;
import com.example.database.Repository.PatientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    public PatientService(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }
    public PatientResponseDTO addPatient(PatientEntryDTO patientEntryDTO) {
        Patient patient = new Patient();
        patient.setGender(patientEntryDTO.getGender());
        patient.setAge(patientEntryDTO.getAge());
        patient.setName(patientEntryDTO.getName());
        patient.setBloodGroup(patientEntryDTO.getBloodGroup());
        patient.setAddress(patientEntryDTO.getAddress());
        patient.setEmail(patientEntryDTO.getEmail());
        patient.setPhone(patientEntryDTO.getPhone());
        patient.setDiseaseHistory(patientEntryDTO.getDiseaseHistory());
        patient.setRegistrationDate(patientEntryDTO.getRegistrationDate());
        Patient savedPatient = patientRepository.save(patient);

        PatientResponseDTO response = new PatientResponseDTO();

        response.setName(savedPatient.getName());
        response.setAge(savedPatient.getAge());
        response.setGender(savedPatient.getGender());
        response.setDiseaseHistory(savedPatient.getDiseaseHistory());

        return response;

    }

    public List<PatientResponseDTO> getPatient() {
        //fetching from entity
        List<Patient> patients = patientRepository.findAll();
        //convert entity to DTO
        List<PatientResponseDTO> patientList  = new ArrayList<>();
        for (Patient patient: patients) {
            PatientResponseDTO patientResponseDTO = new PatientResponseDTO();
            patientResponseDTO.setAge(patient.getAge());
            patientResponseDTO.setName(patient.getName());
            patientResponseDTO.setDiseaseHistory(patient.getDiseaseHistory());
            patientResponseDTO.setGender(patient.getGender());
            patientList.add(patientResponseDTO);

        }
        return patientList;


    }

    public PatientResponseDTO getPatientById(Long id) {
        Patient patient = patientRepository.findById(id).
                orElseThrow(()->new RuntimeException("patient not found with id "+id));
        PatientResponseDTO patientResponseDTO = new PatientResponseDTO();
        patientResponseDTO.setGender(patient.getGender());
        patientResponseDTO.setName(patient.getName());
        patientResponseDTO.setAge(patient.getAge());
        patientResponseDTO.setDiseaseHistory(patient.getDiseaseHistory());
        return patientResponseDTO;

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
