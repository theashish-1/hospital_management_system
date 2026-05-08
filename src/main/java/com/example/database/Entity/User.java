package com.example.database.Entity;

import com.example.database.Entity.type.AuthProviderType;
import com.example.database.Entity.type.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   @Column(unique = true,nullable = false)
    private String username;

   @Column(nullable = false)
   private String password;

   @ElementCollection(fetch = FetchType.EAGER)
   @Enumerated(EnumType.STRING)
   Set<Role> roles = new HashSet<>();

   @Enumerated(EnumType.STRING)
   private AuthProviderType providerType;

    private String providerId;

//    @OneToOne
//    @JoinColumn(name = "patient_id")
//    private Patient patient;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Patient patient;

    //This type cascades all operations, persist (save), merge (update), remove (delete), refresh, and detach. When we apply CascadeType.ALL,
    // any action on the parent will automatically apply to its child entities.


    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }


    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Doctor doctor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
