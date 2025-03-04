package org.example.greetingspring;



import java.util.UUID;

public class TokenGenerator {
    public static String generateToken(String email) {
        return UUID.randomUUID().toString();
    }
}

