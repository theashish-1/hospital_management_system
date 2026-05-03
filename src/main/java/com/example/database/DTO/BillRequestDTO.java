package com.example.database.DTO;

import java.math.BigDecimal;

public class BillRequestDTO {
    private BigDecimal medicine_charge;
    private BigDecimal other_charge;
    private BigDecimal room_charge;
    private BigDecimal test_charge;
    private BigDecimal doctor_fees;
    private Long appointment_id;

    public BigDecimal getMedicine_charge() {
        return medicine_charge;
    }

    public void setMedicine_charge(BigDecimal medicine_charge) {
        this.medicine_charge = medicine_charge;
    }

    public BigDecimal getOther_charge() {
        return other_charge;
    }

    public void setOther_charge(BigDecimal other_charge) {
        this.other_charge = other_charge;
    }

    public BigDecimal getRoom_charge() {
        return room_charge;
    }

    public void setRoom_charge(BigDecimal room_charge) {
        this.room_charge = room_charge;
    }

    public BigDecimal getTest_charge() {
        return test_charge;
    }

    public void setTest_charge(BigDecimal test_charge) {
        this.test_charge = test_charge;
    }

    public Long getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(Long appointment_id) {
        this.appointment_id = appointment_id;
    }

    public BigDecimal getDoctor_fees() {
        return doctor_fees;
    }

    public void setDoctor_fees(BigDecimal doctor_fees) {
        this.doctor_fees = doctor_fees;
    }
}
