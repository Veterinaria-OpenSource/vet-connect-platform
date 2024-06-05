package com.org.vetconnect.platform.appointments.domain.model.commands;

import com.org.vetconnect.platform.appointments.domain.model.valueobjects.BookingDetails;
import com.org.vetconnect.platform.appointments.domain.model.valueobjects.ServiceType;

public record CreateBookingCommand(
        Long petOwnerId,
        Long vetCenterId,
        ServiceType serviceType,
        BookingDetails bookingDetails,
        Double price
) {
}
