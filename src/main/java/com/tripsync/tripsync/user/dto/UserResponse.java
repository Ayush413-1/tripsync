package com.tripsync.tripsync.user.dto;

import com.tripsync.tripsync.user.entity.AccountStatus;
import com.tripsync.tripsync.user.entity.Role;

public record UserResponse(
        Long id,
        String fullname,
        String email,
        Role role,
        AccountStatus status
) {
}
