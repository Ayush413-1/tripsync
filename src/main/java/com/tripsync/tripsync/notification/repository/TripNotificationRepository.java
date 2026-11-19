package com.tripsync.tripsync.notification.repository;

import com.tripsync.tripsync.notification.entity.TripNotification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TripNotificationRepository extends JpaRepository<TripNotification, Long> {
    List<TripNotification> findAllByRecipient_IdOrderByCreatedAtDesc(Long recipientId);

    Optional<TripNotification> findByIdAndRecipient_Id(Long notificationId, Long recipientId);
}
