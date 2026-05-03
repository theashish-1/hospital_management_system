package com.example.database.Entity;

<<<<<<< HEAD
import com.example.database.Entity.type.AuthProviderType;
=======
>>>>>>> 46cbaac4155ca17de5d9faae764d05bf320feb38
import com.example.database.Entity.type.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
<<<<<<< HEAD
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
=======
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
>>>>>>> 46cbaac4155ca17de5d9faae764d05bf320feb38
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   @Column(unique = true,nullable = false)
    private String username;

   @Column(nullable = false)
   private String password;

<<<<<<< HEAD
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


=======
   @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

>>>>>>> 46cbaac4155ca17de5d9faae764d05bf320feb38
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

<<<<<<< HEAD

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
=======
    @OneToOne
    @JoinColumn(name = "doctor_id")
>>>>>>> 46cbaac4155ca17de5d9faae764d05bf320feb38
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

<<<<<<< HEAD
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
=======
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


>>>>>>> 46cbaac4155ca17de5d9faae764d05bf320feb38
}
