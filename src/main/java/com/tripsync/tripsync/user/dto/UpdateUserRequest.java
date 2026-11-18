package com.tripsync.tripsync.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateUserRequest(
        @NotBlank(message = "full name is required")
        @Size(max = 100)
        String fullname,

        @NotBlank(message = "Invalid email format")
        @Size(max = 150)
        String email
) {
}
