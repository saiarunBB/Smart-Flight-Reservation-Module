package com.flight.reservation.module1;

import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

@Service
public class AuthService {

    private final Map<String, String> credentials = new ConcurrentHashMap<>();

    private String sha256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 unavailable", e);
        }
    }

    public boolean register(String email, String password) {
        if (credentials.containsKey(email)) return false;
        credentials.put(email, sha256(password));
        return true;
    }

    public boolean login(String email, String password) {
        String stored = credentials.get(email);
        return stored != null && stored.equals(sha256(password));
    }

    public boolean isRegistered(String email) {
        return credentials.containsKey(email);
    }
}