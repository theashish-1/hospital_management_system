package com.example.database.Entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "bills")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ================= RELATIONSHIPS =================

    // Many Bills → One Patient
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    // One Bill → One Appointment
    @OneToOne
    @JoinColumn(name = "appointment_id", nullable = false)
    private Appointment appointment;

    // ================= BILL DETAILS =================

    private BigDecimal doctorFee;

    private BigDecimal medicineCharges;

    private BigDecimal testCharges;

    private BigDecimal roomCharges;

    private BigDecimal otherCharges;

    private BigDecimal totalAmount;

    private String paymentStatus;
    // PAID / PENDING / PARTIAL

    private LocalDate billDate;

    // GETTERS AND SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public BigDecimal getDoctorFee() {
        return doctorFee;
    }

    public void setDoctorFee(BigDecimal doctorFee) {
        this.doctorFee = doctorFee;
    }

    public BigDecimal getMedicineCharges() {
        return medicineCharges;
    }

    public void setMedicineCharges(BigDecimal medicineCharges) {
        this.medicineCharges = medicineCharges;
    }

    public BigDecimal getTestCharges() {
        return testCharges;
    }

    public void setTestCharges(BigDecimal testCharges) {
        this.testCharges = testCharges;
    }

    public BigDecimal getRoomCharges() {
        return roomCharges;
    }

    public void setRoomCharges(BigDecimal roomCharges) {
        this.roomCharges = roomCharges;
    }

    public BigDecimal getOtherCharges() {
        return otherCharges;
    }

    public void setOtherCharges(BigDecimal otherCharges) {
        this.otherCharges = otherCharges;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public LocalDate getBillDate() {
        return billDate;
    }

    public void setBillDate(LocalDate billDate) {
        this.billDate = billDate;
    }
}
