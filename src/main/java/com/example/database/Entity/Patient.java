package com.example.database.Entity;

import com.example.database.Entity.type.BloodGroupType;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
<<<<<<< HEAD
=======
    @GeneratedValue(strategy = GenerationType.IDENTITY)
>>>>>>> 46cbaac4155ca17de5d9faae764d05bf320feb38
    private Long id;

    private String name;

    private String gender;

    private int age;

    private String phone;

    private String email;

    private String address;

<<<<<<< HEAD
    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user; //mapping between patient and user one to one mapping

=======
>>>>>>> 46cbaac4155ca17de5d9faae764d05bf320feb38
    @Enumerated(EnumType.STRING)
    @Column(name = "blood_group")
    private BloodGroupType bloodGroup;

    @Column(length = 1000)
    private String diseaseHistory;

    private LocalDate registrationDate;

    // Relationship: One Patient → Many Appointments
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Appointment> appointments;

    // ================= GETTERS & SETTERS =================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BloodGroupType getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(BloodGroupType bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getDiseaseHistory() {
        return diseaseHistory;
    }

    public void setDiseaseHistory(String diseaseHistory) {
        this.diseaseHistory = diseaseHistory;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
<<<<<<< HEAD

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
=======
>>>>>>> 46cbaac4155ca17de5d9faae764d05bf320feb38
}
