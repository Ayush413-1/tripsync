package com.tripsync.tripsync.expense.dto;

import com.tripsync.tripsync.expense.entity.ExpensesCategory;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateExpenseRequest(
                                   @NotNull @Size(max = 150)
                                   String title,

                                   @Size(max = 1000)
                                   String description,

                                   @NotNull
                                   ExpensesCategory category,

                                   @NotNull
                                   @DecimalMin(value = "0.01")
                                   @Digits(integer = 10, fraction = 2)
                                   BigDecimal amount,

                                   @NotNull
                                   LocalDate expenseDate,

                                   @NotNull
                                   @Size(min = 1)
                                   List<@Valid ExpenseSplitRequest>splits
                                   ) {
}
