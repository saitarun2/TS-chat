package com.hooks.pingme.model;

public record ChatMessage(String type, String username, String text, String timestamp) {
}
