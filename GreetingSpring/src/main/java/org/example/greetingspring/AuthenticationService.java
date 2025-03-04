package org.example.greetingspring;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public AuthenticationService(AuthUserRepository authUserRepository,
                                 PasswordEncoder passwordEncoder,
                                 EmailService emailService) {
        this.authUserRepository = authUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    // ✅ Handle User Registration
    public String registerUser(AuthUserDTO userDTO) {
        if (authUserRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered!");
        }

        AuthUser newUser = AuthUser.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword())) // Hash password
                .build();

        authUserRepository.save(newUser);

        // 🔹 Generate verification token
        String token = TokenGenerator.generateToken(newUser.getEmail());

        // 🔹 Send confirmation email
        emailService.sendEmail(newUser.getEmail(), "Welcome!",
                "Your account has been created! Your verification token: " + token);

        return "User registered successfully! Please check your email.";
    }

    // ✅ Handle User Login
    public String loginUser(LoginDTO loginDTO) {
        Optional<AuthUser> userOpt = authUserRepository.findByEmail(loginDTO.getEmail());

        if (userOpt.isEmpty() || !passwordEncoder.matches(loginDTO.getPassword(), userOpt.get().getPassword())) {
            throw new RuntimeException("Invalid email or password!");
        }

        emailService.sendEmail(loginDTO.getEmail(), "Login Alert", "You have logged in successfully.");

        return "Login successful!";
    }
}

