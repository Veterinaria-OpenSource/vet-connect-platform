package com.org.vetconnect.platform.appointments.domain.model.queries;

import java.time.LocalDateTime;

public record GetBookingsInWeekQuery(
        LocalDateTime startOfWeek,
        LocalDateTime endOfWeek
) {
}
