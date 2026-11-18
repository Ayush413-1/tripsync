package com.tripsync.tripsync.expense.controller;


import com.tripsync.tripsync.expense.dto.CreateExpenseRequest;
import com.tripsync.tripsync.expense.dto.MemberBalanceResponse;
import com.tripsync.tripsync.expense.service.BalanceService;
import com.tripsync.tripsync.expense.service.ExpenseService;
import com.tripsync.tripsync.settlement.service.SettlementService;
import com.tripsync.tripsync.trip.repository.TripMemberRepository;
import com.tripsync.tripsync.user.entity.User;
import com.tripsync.tripsync.user.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/trips/{tripId}")
public class ExpenseController {
    private final ExpenseService expenseService;
    private final SettlementService settlementService;
    private final BalanceService balanceService;
    private final UserRepository userRepository;
    private final TripMemberRepository tripMemberRepository;


    @PostMapping("/expenses")
    private ResponseEntity<Void> createExpense(
            @PathVariable Long tripId,
            @Valid @RequestBody CreateExpenseRequest request,
            Authentication authentication
            ){
        User currentUser = getCurrentUser(authentication);
        expenseService.createExpense(tripId,currentUser.getId(), request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



    @GetMapping("/balances")
    public ResponseEntity<List<MemberBalanceResponse>> getBalances
            (
                    @PathVariable Long tripId,
                    Authentication authentication
            ){
        User currentUser = getCurrentUser(authentication);
        if(!tripMemberRepository.existsByTrip_IdAndUser_Id(tripId,currentUser.getId())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(balanceService.getBalances(tripId));
    }


    private User getCurrentUser(Authentication authentication){
        return userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new IllegalArgumentException("Authenticated user not found"));
    }



}
