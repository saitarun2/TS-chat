package com.hooks.pingme.model;

public record AuthResponse(boolean success, String message, String username) {
}
