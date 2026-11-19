
package com.tripsync.tripsync.invitation.repository;

import com.tripsync.tripsync.invitation.entity.TripInvitation;
import com.tripsync.tripsync.invitation.entity.Invitationstatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TripInvitationRepository
        extends JpaRepository<TripInvitation, Long> {

    List<TripInvitation> findByInviteeEmailIgnoreCase(String inviteeEmail);

    List<TripInvitation> findByTripId(Long tripId);

    Optional<TripInvitation> findByIdAndInviteeEmailIgnoreCase(
            Long id, String inviteeEmail);

    Optional<TripInvitation> findByTokenHash(String tokenHash);

    boolean existsByTrip_IdAndInviteeEmailIgnoreCaseAndStatus(
            Long tripId, String inviteeEmail, Invitationstatus status);
}
