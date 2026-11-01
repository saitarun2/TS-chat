package com.hooks.pingme.service;

import com.hooks.pingme.model.AuthRequest;
import com.hooks.pingme.model.AuthResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AuthService {
    // In-memory demo store: accounts disappear when the application restarts.
    // Passwords are hashed, never stored in plaintext.
    private final Map<String, String> passwordHashes = new ConcurrentHashMap<>();
    private final PasswordEncoder passwordEncoder;

    public AuthService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponse register(AuthRequest request) {
        String username = normalize(request.username());
        if (username.length() < 3 || username.length() > 24) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Username must contain 3 to 24 characters.");
        }
        String hash = passwordEncoder.encode(request.password());
        if (passwordHashes.putIfAbsent(username, hash) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "That username is already taken.");
        }
        return new AuthResponse(true, "Your account is ready. Welcome to PingMe!", username);
    }

    public AuthResponse login(AuthRequest request) {
        String username = normalize(request.username());
        String storedHash = passwordHashes.get(username);
        if (storedHash == null || !passwordEncoder.matches(request.password(), storedHash)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password is incorrect.");
        }
        return new AuthResponse(true, "Welcome back!", username);
    }

    private String normalize(String username) {
        return username == null ? "" : username.trim().toLowerCase(Locale.ROOT);
    }
}
