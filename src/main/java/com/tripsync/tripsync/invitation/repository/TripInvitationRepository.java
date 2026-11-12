
package com.tripsync.tripsync.invitation.repository;

import com.tripsync.tripsync.invitation.entity.Tripinvitation;
import com.tripsync.tripsync.invitation.entity.Invitationstatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TripInvitationRepository
        extends JpaRepository<Tripinvitation, Long> {

    List<Tripinvitation> findByInviteeEmailIgnoreCase(String inviteeEmail);

    List<Tripinvitation> findByTripId(Long tripId);

    Optional<Tripinvitation> findByIdAndInviteeEmailIgnoreCase(
            Long id, String inviteeEmail);

    Optional<Tripinvitation> findByTokenHash(String tokenHash);

    boolean existsByTrip_IdAndInviteeEmailIgnoreCaseAndStatus(
            Long tripId, String inviteeEmail, Invitationstatus status);
}
