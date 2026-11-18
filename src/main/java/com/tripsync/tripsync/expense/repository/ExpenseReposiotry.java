package com.tripsync.tripsync.expense.repository;

import com.tripsync.tripsync.expense.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExpenseReposiotry extends JpaRepository<Expense, Long> {
    List<Expense> findByTrip_IdOrderByExpenseDateDesc(Long tripId);

    Optional<Expense> findByIdAndTrip_Id(Long id, Long tripId);

    List<Expense> findByPaidBy_IdAndTrip_Id(Long userId, Long tripId);
}
