package com.org.vetconnect.platform.profiles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record VetCenterRUC(Long VetCenterRUC) {

    // ruc not null and ruc length is 11
    public VetCenterRUC(Long VetCenterRUC) {
        if (VetCenterRUC != null && VetCenterRUC.toString().length() == 11) {
            this.VetCenterRUC = VetCenterRUC;
        } else {
            throw new IllegalArgumentException("RUC must be 11 digits");
        }
    }

    public Long getVetCenterRUC() {
        return VetCenterRUC;
    }
}
