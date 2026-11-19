package com.tripsync.tripsync.Itinerary.dto;



import java.time.LocalDate;

import java.util.List;

public record ItineraryResponse(
        Long id,
        Long tripid,
        LocalDate date,
        String note,
        List<ActivityResponse> activities
) {
}
