
package com.tripsync.tripsync.expense.service;

import com.tripsync.tripsync.expense.dto.MemberBalanceResponse;
import com.tripsync.tripsync.expense.entity.ExpenseSplit;
import com.tripsync.tripsync.expense.repository.ExpenseReposiotry;
import com.tripsync.tripsync.expense.repository.ExpenseSplitReposiotry;
import com.tripsync.tripsync.settlement.entity.Settlement;
import com.tripsync.tripsync.settlement.repository.SettlementRepository;
import com.tripsync.tripsync.expense.entity.Expense;
import com.tripsync.tripsync.trip.entity.TripMember;
import com.tripsync.tripsync.trip.repository.TripMemberRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BalanceService {

    private final TripMemberRepository tripMemberRepository;
    private final ExpenseReposiotry expenseRepository;
    private final ExpenseSplitReposiotry splitRepository;
    private final SettlementRepository settlementRepository;

    @Transactional(readOnly = true)
    public List<MemberBalanceResponse> getBalances(Long tripId) {

        List<TripMember> members =
                tripMemberRepository.findByTrip_Id(tripId);

        List<Expense> expenses =
                expenseRepository.findByTrip_IdOrderByExpenseDateDesc(tripId);

        List<Settlement> settlements =
                settlementRepository.findByTrip_IdOrderBySettleAtDesc(tripId);

        List<MemberBalanceResponse> results = new ArrayList<>();

        for (TripMember member : members) {

            Long userId = member.getUser().getId();

            BigDecimal totalPaid = expenses.stream()
                    .filter(e -> e.getPaidBy().getId().equals(userId))
                    .map(Expense::getAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            List<ExpenseSplit> splits =
                    splitRepository.findByExpense_Trip_IdAndUser_Id(tripId, userId);

            BigDecimal totalShare = splits.stream()
                    .map(ExpenseSplit::getShareAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            BigDecimal settlementsSent = settlements.stream()
                    .filter(s -> s.getPayer().getId().equals(userId))
                    .map(Settlement::getAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            BigDecimal settlementReceived = settlements.stream()
                    .filter(s -> s.getPayee().getId().equals(userId))
                    .map(Settlement::getAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            // Net Balance = Paid - Share + Sent - Received
            BigDecimal netBalance = totalPaid
                    .subtract(totalShare)
                    .add(settlementsSent)
                    .subtract(settlementReceived);

            results.add(new MemberBalanceResponse(
                    userId,
                    member.getUser().getFullName(),
                    totalPaid,
                    totalShare,
                    settlementsSent,
                    settlementReceived,
                    netBalance
            ));
        }

        return results;
    }
}


