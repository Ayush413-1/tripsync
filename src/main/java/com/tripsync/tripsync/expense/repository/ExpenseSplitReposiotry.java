package com.tripsync.tripsync.expense.repository;

import com.tripsync.tripsync.trip.entity.Expense;
import com.tripsync.tripsync.trip.entity.ExpenseSplit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseSplitReposiotry extends JpaRepository<ExpenseSplit, Long> {
    List<ExpenseSplit> findByExpenseId(Long expenseId);
    List<ExpenseSplit> findByUserId(Long userId);
    List<ExpenseSplit> findByExpenseTripIdAndUserId(Long tripId,Long userId);
}
