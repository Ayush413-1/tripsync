package com.tripsync.tripsync.user.controller;

import com.tripsync.tripsync.user.dto.AdminResetPasswordRequest;
import com.tripsync.tripsync.user.dto.UpdateUserRequest;
import com.tripsync.tripsync.user.dto.UpdateUserStatusRequest;
import com.tripsync.tripsync.user.dto.UserResponse;
import com.tripsync.tripsync.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/users")
@PreAuthorize("hasRole('ADMIN')")
public class AdminUserController {

    private final UserService userService;

    public ResponseEntity<List<UserResponse>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }


    @PatchMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @Valid @RequestBody UpdateUserRequest request){
        return ResponseEntity.ok(userService.updateUser(id,request));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<UserResponse> updateUserStatus(@PathVariable Long id, @Valid @RequestBody UpdateUserStatusRequest request){
        return ResponseEntity.ok(userService.updateUserStatus(id, request.status()));
    }


    @PatchMapping("/{id}/reset-password")
    public ResponseEntity<Void> resetPasswordRequest(@PathVariable Long id, @Valid @RequestBody AdminResetPasswordRequest request){
        userService.resetPassword(id, request.newPassword());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
