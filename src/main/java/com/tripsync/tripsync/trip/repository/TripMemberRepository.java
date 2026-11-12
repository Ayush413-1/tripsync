package com.tripsync.tripsync.trip.repository;

import com.tripsync.tripsync.trip.entity.TripMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TripMemberRepository extends JpaRepository<TripMember, Long> {
    List<TripMember> findByTripId(Long id);
    List<TripMember> findByUserId(Long userId);
    Optional<TripMember> findByTripAndUserId(Long tripId, Long userId);
    boolean existsByTripIdAndUserId(Long tripId,Long userId);
}
