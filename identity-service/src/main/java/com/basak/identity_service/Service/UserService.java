package com.basak.identity_service.Service;

import com.basak.identity_service.DTO.RegisterRequest;
import com.basak.identity_service.DTO.UserDTO;
import com.basak.identity_service.Model.User;
import com.basak.identity_service.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO register(RegisterRequest request) {
        // 1. Validate
        if (request.getEmail() == null || request.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (request.getPassword() == null || request.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password is required");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        // 2. Hash password
        String hashedPassword = passwordEncoder.encode(request.getPassword());

        // 3. Save user
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(hashedPassword);
        user.setRole(request.getRole() != null ? request.getRole() : "USER");

        User savedUser = userRepository.save(user);

        // 4. Return DTO
        return new UserDTO(savedUser.getId(), savedUser.getEmail(), savedUser.getRole());
    }
}
