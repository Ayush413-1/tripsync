package com.tripsync.tripsync.user.dto;

import com.tripsync.tripsync.user.entity.AccountStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateUserStatusRequest(
        @NotNull(message = "Account status is required")
        AccountStatus status
) {
}
