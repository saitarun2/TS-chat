package com.hooks.pingme.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hooks.pingme.model.ChatMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {
    private final Set<WebSocketSession> sessions = ConcurrentHashMap.newKeySet();
    private final ObjectMapper objectMapper;
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");

    public ChatWebSocketHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        send(session, new ChatMessage("system", "PingMe", "You're connected. Say hello 👋",
                LocalTime.now().format(TIME_FORMAT)));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage incoming) throws Exception {
        JsonNode body = objectMapper.readTree(incoming.getPayload());
        String username = safeText(body.path("username").asText("Guest"), 24);
        String message = safeText(body.path("text").asText(""), 1000).trim();
        if (message.isBlank()) {
            return;
        }
        ChatMessage outgoing = new ChatMessage("chat",
                username.isBlank() ? "Guest" : username,
                message,
                LocalTime.now().format(TIME_FORMAT));
        String json = objectMapper.writeValueAsString(outgoing);
        for (WebSocketSession client : sessions) {
            if (client.isOpen()) {
                synchronized (client) {
                    client.sendMessage(new TextMessage(json));
                }
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        sessions.remove(session);
        if (session.isOpen()) {
            session.close(CloseStatus.SERVER_ERROR);
        }
    }

    private void send(WebSocketSession session, ChatMessage message) throws Exception {
        session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
    }

    private String safeText(String value, int maxLength) {
        if (value == null) return "";
        String clean = value.replaceAll("[\\p{Cntrl}&&[^\\n\\t]]", "");
        return clean.length() > maxLength ? clean.substring(0, maxLength) : clean;
    }
}
