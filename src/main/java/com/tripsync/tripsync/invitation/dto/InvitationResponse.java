package com.tripsync.tripsync.invitation.dto;

import com.tripsync.tripsync.invitation.entity.Invitationstatus;
import com.tripsync.tripsync.trip.entity.TripMemberRole;

import java.time.LocalDateTime;

public record InvitationResponse(
        Long invitationId,
        Long tripId,
        String inviteeEmail,
        TripMemberRole proposedRole,
        Invitationstatus status,
        LocalDateTime expiresAt
) {
}
