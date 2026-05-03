package com.example.database.Service;

import com.example.database.DTO.DoctorProfileResponseDTO;
import com.example.database.DTO.DoctorProfileUpdateDTO;
import com.example.database.DTO.DoctorRequestDTO;
import com.example.database.DTO.DoctorResponseDTO;
import com.example.database.DTO.DoctorViewResponseDTO;
import com.example.database.Entity.Doctor;
import com.example.database.Entity.User;
import com.example.database.Entity.type.AuthProviderType;
import com.example.database.Repository.DoctorRepository;
import com.example.database.Repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.management.relation.Role;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Transactional
    public DoctorResponseDTO addDoctor(DoctorRequestDTO doctorRequestDTO) {
        //Building a user
        User user = User.builder()
                .username(doctorRequestDTO.getEmail())
                .password(passwordEncoder.encode("Sample@123"))
                .roles(Set.of(com.example.database.Entity.type.Role.DOCTOR))
                .providerType(AuthProviderType.EMAIL)
                .build();
        //checking if doctor already exist 
        Doctor doctor = new Doctor();
        doctor.setName(doctorRequestDTO.getName());
        doctor.setSpecialization(doctorRequestDTO.getSpecialization());
        doctor.setAge(doctorRequestDTO.getAge());
        doctor.setExperience(doctorRequestDTO.getExperience());
        doctor.setPhone(doctorRequestDTO.getPhone());
        doctor.setEmail(doctorRequestDTO.getEmail());
        doctor.setConsultationFee(doctorRequestDTO.getConsultationFee());
        doctor.setAvailableDays(doctorRequestDTO.getAvailableDaysList());
        doctor.setUser(user);
        user.setDoctor(doctor);

        User savedUser = userRepository.save(user);

        return mapToResponseDTODoctor(savedUser);

        
      
    }

    private DoctorResponseDTO mapToResponseDTODoctor(User savedUser) {
        // TODO Auto-generated method stub
        DoctorResponseDTO doctorResponseDTO = new DoctorResponseDTO();
        doctorResponseDTO.setName(savedUser.getDoctor().getName());
        doctorResponseDTO.setSpecialization(savedUser.getDoctor().getSpecialization());
        doctorResponseDTO.setAge(savedUser.getDoctor().getAge());       
        doctorResponseDTO.setExperience(savedUser.getDoctor().getExperience());
        doctorResponseDTO.setPhone(savedUser.getDoctor().getPhone());
        doctorResponseDTO.setEmail(savedUser.getDoctor().getEmail());
        doctorResponseDTO.setConsultationFee(savedUser.getDoctor().getConsultationFee());

        return doctorResponseDTO;
    }


    public List<DoctorViewResponseDTO> getDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        List<DoctorViewResponseDTO> doctorViewResponseDTOList = new ArrayList<>();
        for (Doctor doctor : doctors) {
            DoctorViewResponseDTO doctorViewResponseDTO = new DoctorViewResponseDTO();
            doctorViewResponseDTO.setName(doctor.getName());
            doctorViewResponseDTO.setSpecialization(doctor.getSpecialization());
            doctorViewResponseDTO.setExperience(doctor.getExperience());
            // doctorViewResponseDTO.setPhone(doctor.getPhone());
            doctorViewResponseDTO.setEmail(doctor.getEmail());
            doctorViewResponseDTO.setConsultationFee(doctor.getConsultationFee());
            doctorViewResponseDTOList.add(doctorViewResponseDTO);
        }

        return doctorViewResponseDTOList;
    }

    public Optional<Doctor> getDoctorByid(Long id) {
        return doctorRepository.findById(id);
    }

    public Doctor updateDoctor(Doctor doctor,Long id) {
        Doctor isExisting = doctorRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("Doctor not found with id : "+id));

//        System.out.println("Updating doctor ID = " + isExisting.getId());
        isExisting.setName(doctor.getName());
        isExisting.setSpecialization(doctor.getSpecialization());
        isExisting.setAge(doctor.getAge());
        return doctorRepository.save(isExisting);

    }

    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }
    public List<Doctor> getDoctorsBySpecialization(String specialization) {
        return doctorRepository.findBySpecializationIgnoreCase(specialization);
    }

    public List<Doctor> getDoctorByYearOfExperience(int year) {
        return doctorRepository.findByExperience(year);
    }


    @Transactional
    public DoctorProfileResponseDTO updateDoctorProfile(Long id, DoctorProfileUpdateDTO doctorProfileUpdateDTO) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow( ()-> new RuntimeException("Doctor profile not found for ID: " + id));

        doctor.setSpecialization(doctorProfileUpdateDTO.getSpecialization());
        doctor.setAge(doctorProfileUpdateDTO.getAge());
        doctor.setExperience(doctorProfileUpdateDTO.getExperience());
        doctor.setPhone(doctorProfileUpdateDTO.getPhone());
        doctor.setEmail(doctorProfileUpdateDTO.getEmail());
        doctor.setConsultationFee(doctorProfileUpdateDTO.getConsultationFee());
        doctor.setAvailableDays(doctorProfileUpdateDTO.getAvailableDays());

        Doctor savedDoctor = doctorRepository.save(doctor);

        // Return the mapped Response DTO
        return mapToResponseDTO(savedDoctor);
    }

    private DoctorProfileResponseDTO mapToResponseDTO(Doctor savedDoctor) {
        return DoctorProfileResponseDTO.builder()
                .id(savedDoctor.getId())
                .name(savedDoctor.getName())
                .specialization(savedDoctor.getSpecialization())
                .age(savedDoctor.getAge())
                .experience(savedDoctor.getExperience())
                .phone(savedDoctor.getPhone())
                .email(savedDoctor.getEmail())
                .consultationFee(savedDoctor.getConsultationFee())
                .availableDays(savedDoctor.getAvailableDays())
                .build();
    }
}
