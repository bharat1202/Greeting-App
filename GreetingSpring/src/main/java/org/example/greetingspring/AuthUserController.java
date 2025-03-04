package org.example.greetingspring;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthUserController {

    private final AuthUserService authUserService;

    // ✅ 1. Register a New User
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody AuthUserDTO authUserDTO) {
        authUserService.registerUser(authUserDTO);
        return ResponseEntity.ok("User registered successfully!");
    }

    // ✅ 2. User Login
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDTO loginDTO) {
        boolean isAuthenticated = authUserService.authenticateUser(loginDTO);
        return isAuthenticated
                ? ResponseEntity.ok("Login successful!")
                : ResponseEntity.status(401).body("Invalid credentials!");
    }
}

