package com.tripsync.tripsync.trip.dto;

import java.time.LocalDate;

public record TripResponse(
        Long id,
        String title,
        String description,
        String destination,
        LocalDate startDate,
        LocalDate endDate,
        Long OwnerId,
        String ownerName
) {
}
