package com.tripsync.tripsync.expense.repository;

import com.tripsync.tripsync.expense.entity.Settlement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SettlementRepository extends JpaRepository<Settlement , Long> {

    List<Settlement> findByTripOrderBySettlementAtDesc(Long tripId);
    List<Settlement> findByTripidAndPayerId(Long tripId, Long payerId);
    List<Settlement> findByTripidAndPayeeId(Long tripId, Long payeeId);
}
