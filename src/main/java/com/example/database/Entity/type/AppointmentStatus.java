package com.example.database.Entity.type;

public enum AppointmentStatus {
    BOOKED,        // Appointment created successfully

    CONFIRMED,     // Doctor/clinic confirmed

    COMPLETED,     // Consultation finished

    CANCELLED,     // Cancelled by patient or doctor

    NO_SHOW,       // Patient did not arrive

    RESCHEDULED
}
