package com.tripsync.tripsync.Itinerary.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CreateItineraryRequest(

        @NotNull(message = "Itinerary date is required")
        LocalDate date,

        @Size(max = 100)
        String note
) {
}
