package com.hooks.pingme.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthRequest(
        @NotBlank @Size(min = 3, max = 24) String username,
        @NotBlank @Size(min = 8, max = 72) String password) {
}
