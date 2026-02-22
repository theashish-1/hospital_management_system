package com.example.database.Service;

import com.example.database.Entity.Doctor;
import com.example.database.Repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    public DoctorService(DoctorRepository doctorRepository){
        this.doctorRepository = doctorRepository;
    }
    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public List<Doctor> getDoctors() {
        return doctorRepository.findAll();
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
}
