package com.example.database.DTO;

import com.example.database.Entity.type.BloodGroupType;

<<<<<<< HEAD
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
@Data
@Builder
@AllArgsConstructor
public class PatientEntryDTO {
    private Long userId;
=======
import java.time.LocalDate;

public class PatientEntryDTO {
>>>>>>> 46cbaac4155ca17de5d9faae764d05bf320feb38
    private String name;
    private int age;
    private String address;
    private String diseaseHistory;
    private String email;
    private String gender;
    private LocalDate registrationDate;
    private BloodGroupType bloodGroup;
    private String phone;

<<<<<<< HEAD
    public Long getuserId() {
        return userId;
    }

    public void setId(Long id) {
        userId = id;
    }

=======
>>>>>>> 46cbaac4155ca17de5d9faae764d05bf320feb38
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDiseaseHistory() {
        return diseaseHistory;
    }

    public void setDiseaseHistory(String diseaseHistory) {
        this.diseaseHistory = diseaseHistory;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public BloodGroupType getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(BloodGroupType bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
