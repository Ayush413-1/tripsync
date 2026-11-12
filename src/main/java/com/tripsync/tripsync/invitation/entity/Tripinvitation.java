package com.tripsync.tripsync.invitation.entity;

import com.tripsync.tripsync.trip.entity.Trip;
import com.tripsync.tripsync.trip.entity.TripMemberRole;
import com.tripsync.tripsync.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "invitations")
public class Tripinvitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

    @Column(name = "token_hash", nullable = false, unique = true)
    private String tokenHash;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "invited_by_id", nullable = false)
    private User invitedBy;

    @Column(name = "invitee_email", nullable = false, length = 150)
    private String inviteeEmail;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TripMemberRole proposedRole = TripMemberRole.VIEWER;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Invitationstatus status = Invitationstatus.PENDING;

    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expiresAt;
}
