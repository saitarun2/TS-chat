package com.hooks.pingme.repo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hooks.pingme.model.Users;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UsersRepo {

    private final File tempFile = new File(System.getProperty("java.io.tmpdir"), "pingme_users.json");
    private final ObjectMapper objectMapper = new ObjectMapper();

    public UsersRepo() {
        if (!tempFile.exists()) {
            saveAll(new ArrayList<>());
        }
    }

    public List<Users> findAll() {
        try {
            return objectMapper.readValue(tempFile, new TypeReference<List<Users>>() {});
        } catch (IOException e) {
            System.err.println("Error reading users from temp file: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public Users save(Users user) {
        List<Users> usersList = findAll();

        // Remove existing user with the same username if updating
        usersList.removeIf(u -> u.getUsername().equals(user.getUsername()));

        usersList.add(user);
        saveAll(usersList);
        return user;
    }

    public Users findByUsername(String username) {
        return findAll().stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    private void saveAll(List<Users> usersList) {
        try {
            objectMapper.writeValue(tempFile, usersList);
        } catch (IOException e) {
            System.err.println("Error writing users to temp file: " + e.getMessage());
        }
    }
}