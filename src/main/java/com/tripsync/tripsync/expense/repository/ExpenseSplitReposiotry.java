
package com.tripsync.tripsync.expense.repository;

import com.tripsync.tripsync.expense.entity.ExpenseSplit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseSplitReposiotry extends JpaRepository<ExpenseSplit, Long> {

    List<ExpenseSplit> findByExpense_Id(Long expenseId);

    List<ExpenseSplit> findByUser_Id(Long userId);

    List<ExpenseSplit> findByExpense_Trip_IdAndUser_Id(
            Long tripId, Long userId
    );
}
