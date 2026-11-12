package com.tripsync.tripsync.expense.repository;

import com.tripsync.tripsync.expense.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExpenseReposiotry extends JpaRepository<Expense, Long> {
    List<Expense> findByTripOrderByExpenseDateDesc(Long TripId);
    Optional<Expense> findByIdAndTripid(Long id, Long TripId);
    List<Expense> findByPaidByIdAndTripId(Long userId, Long tripId);
}
