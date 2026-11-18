
package com.tripsync.tripsync.settlement.repository;

import com.tripsync.tripsync.settlement.entity.Settlement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SettlementRepository
        extends JpaRepository<Settlement, Long> {

    List<Settlement> findByTrip_IdOrderBySettleAtDesc(Long tripId);
}
