package org.example.greetingspring;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthUserService {

    private final AuthUserRepository authUserRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    // ✅ Register a New User
    public void registerUser(AuthUserDTO authUserDTO) {
        AuthUser user = new AuthUser();
        user.setFirstName(authUserDTO.getFirstName());
        user.setLastName(authUserDTO.getLastName());
        user.setEmail(authUserDTO.getEmail());
        user.setPassword(passwordEncoder.encode(authUserDTO.getPassword())); // Hash password
        authUserRepository.save(user);
    }

    // ✅ Authenticate User
    public boolean authenticateUser(LoginDTO loginDTO) {
        Optional<AuthUser> userOpt = authUserRepository.findByEmail(loginDTO.getEmail());
        return userOpt.isPresent() && passwordEncoder.matches(loginDTO.getPassword(), userOpt.get().getPassword());
    }
}


