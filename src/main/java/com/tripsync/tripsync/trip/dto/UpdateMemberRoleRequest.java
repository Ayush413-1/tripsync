package com.tripsync.tripsync.trip.dto;

import com.tripsync.tripsync.trip.entity.TripMemberRole;
import jakarta.validation.constraints.NotNull;

public record UpdateMemberRoleRequest(
        @NotNull(message = "Member role is required")
        TripMemberRole role
) {
}
