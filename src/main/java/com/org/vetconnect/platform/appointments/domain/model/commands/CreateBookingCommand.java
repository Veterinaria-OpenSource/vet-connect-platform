package com.org.vetconnect.platform.appointments.domain.model.commands;

public record CreateBookingCommand(
        Long petOwnerId,
        Long vetCenterId,
        String serviceType,
        String bookingDetails,
        Double price
) {
}
