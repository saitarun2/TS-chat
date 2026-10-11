package com.hooks.pingme.repo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hooks.pingme.model.Message;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class MessageRepo {

    private final File tempFile = new File(System.getProperty("java.io.tmpdir"), "pingme_messages.json");
    private final ObjectMapper objectMapper = new ObjectMapper();

    public MessageRepo() {
        if (!tempFile.exists()) {
            saveAll(new ArrayList<>());
        }
    }

    public List<Message> findAll() {
        try {
            return objectMapper.readValue(tempFile, new TypeReference<List<Message>>() {});
        } catch (IOException e) {
            System.err.println("Error reading messages from temp file: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public Message save(Message message) {
        List<Message> messages = findAll();

        // Generate an ID if it doesn't have one
        if (message.getId() == null || message.getId().isEmpty()) {
            message.setId(UUID.randomUUID().toString());
        }

        messages.add(message);
        saveAll(messages);
        return message;
    }

    private void saveAll(List<Message> messages) {
        try {
            objectMapper.writeValue(tempFile, messages);
        } catch (IOException e) {
            System.err.println("Error writing messages to temp file: " + e.getMessage());
        }
    }
}