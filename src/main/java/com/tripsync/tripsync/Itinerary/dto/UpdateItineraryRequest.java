package com.tripsync.tripsync.Itinerary.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UpdateItineraryRequest(

        @NotNull(message = "Itineary date is required")
        LocalDate date,

        @Size(max = 1000)
        String note
) {
}
