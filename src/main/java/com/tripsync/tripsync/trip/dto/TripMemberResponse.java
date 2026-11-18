package com.tripsync.tripsync.trip.dto;

import com.tripsync.tripsync.trip.entity.TripMemberRole;

import java.time.LocalDateTime;

public record TripMemberResponse(
        Long userId,
        String  fullName,
        String email,
        TripMemberRole role,
        LocalDateTime joinedAt
) {
}
