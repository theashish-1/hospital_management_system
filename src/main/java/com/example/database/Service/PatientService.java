package com.example.database.Service;

import com.example.database.DTO.PatientEntryDTO;
<<<<<<< HEAD
import com.example.database.DTO.PatientProfileResponse;
import com.example.database.DTO.PatientProfileUpdateDTO;
import com.example.database.DTO.PatientResponseDTO;
import com.example.database.Entity.Patient;
import com.example.database.Entity.User;
import com.example.database.Repository.PatientRepository;
import com.example.database.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.jspecify.annotations.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
=======
import com.example.database.DTO.PatientResponseDTO;
import com.example.database.Entity.Patient;
import com.example.database.Repository.PatientRepository;
import org.springframework.http.ResponseEntity;
>>>>>>> 46cbaac4155ca17de5d9faae764d05bf320feb38
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
<<<<<<< HEAD
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;
    public PatientResponseDTO addPatient(PatientEntryDTO patientEntryDTO) {
        User targetUser;
        //to het currently logged in username fron that token 
        org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin  = auth.getAuthorities().stream()
                          .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        if(isAdmin && patientEntryDTO.getuserId() != null){
            //Admin is trying to add a patient 
            targetUser = userRepository.findById(patientEntryDTO.getuserId())
                    .orElseThrow(()->new RuntimeException("User not found ")); 
        }else{
            String userName = auth.getName();
            targetUser = userRepository.findByUsername(userName)
            .orElseThrow(() -> new RuntimeException("User not found"));
        }
        // 2. CHECK IF PROFILE ALREADY EXISTS (Prevents NonUniqueObjectException)
        // If it exists, we find the existing one instead of 'new Patient()'
        Patient patient = patientRepository.findById(targetUser.getId())
            .orElse(new Patient());
        // Patient patient = new Patient();
        patient.setUser(targetUser);
=======
public class PatientService {

    private final PatientRepository patientRepository;
    public PatientService(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }
    public PatientResponseDTO addPatient(PatientEntryDTO patientEntryDTO) {
        Patient patient = new Patient();
>>>>>>> 46cbaac4155ca17de5d9faae764d05bf320feb38
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
<<<<<<< HEAD

    public PatientProfileResponse updatePatientProfile(PatientProfileUpdateDTO patientProfileUpdateDTO,Long id) {
        System.out.println("blood_group"+patientProfileUpdateDTO.getBloodGroupType());
        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Patient already exist "));

        existingPatient.setAge(Math.toIntExact(patientProfileUpdateDTO.getAge()));
        existingPatient.setGender(patientProfileUpdateDTO.getGender());
        existingPatient.setPhone(patientProfileUpdateDTO.getPhone());
        existingPatient.setRegistrationDate(patientProfileUpdateDTO.getRegistrationDate());
        existingPatient.setAddress(patientProfileUpdateDTO.getAddress());
        existingPatient.setDiseaseHistory(patientProfileUpdateDTO.getDisease_history());
        existingPatient.setBloodGroup(patientProfileUpdateDTO.getBloodGroupType());
        existingPatient.setEmail(patientProfileUpdateDTO.getEmail());

        Patient savedPatient = patientRepository.save(existingPatient);
        return mapToDTO(savedPatient);


    }

    private PatientProfileResponse mapToDTO(Patient savedPatient) {

        return PatientProfileResponse.builder()
                .age((long) savedPatient.getAge())
                .email(savedPatient.getEmail())
                .phone(savedPatient.getPhone())
                .bloodGroupType(savedPatient.getBloodGroup())
                .address(savedPatient.getAddress())
                .disease_history(savedPatient.getDiseaseHistory())
                .gender(savedPatient.getGender())
                .registrationDate(savedPatient.getGender())
                .build();
    }
=======
>>>>>>> 46cbaac4155ca17de5d9faae764d05bf320feb38
}
