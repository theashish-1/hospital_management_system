package com.example.database.Entity.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;


public enum BloodGroupType {
    A_POSITIVE("A+"),
    A_NEGATIVE("A-"),
    B_POSITIVE("B+"),
    B_NEGATIVE("B-"),
    AB_POSITIVE("AB+"),
    AB_NEGATIVE("AB-"),
    O_POSITIVE("O+"),
    O_NEGATIVE("O-");

    final String display;
    BloodGroupType(String display){
        this.display = display;
    }
    @JsonValue
    public String getDisplay() {
        return display;
    }
    @JsonCreator
    public static BloodGroupType fromValue(String value) {

        for (BloodGroupType type : values()) {
            if (type.display.equalsIgnoreCase(value)) {
                return type;
            }
        }

        throw new IllegalArgumentException(
                "Invalid blood group: " + value
        );
    }


}
