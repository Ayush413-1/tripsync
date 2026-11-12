package com.tripsync.tripsync.Itinerary.repository;

import com.tripsync.tripsync.Itinerary.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findByItineraryIdOrderByDisplayOrderAsc(Long itineraryId);
    Optional<Activity> findByIdAndItineraryId(Long id, Long itineraryId);
}
