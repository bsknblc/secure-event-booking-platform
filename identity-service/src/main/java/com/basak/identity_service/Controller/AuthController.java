package com.basak.identity_service.Controller;

import com.basak.identity_service.DTO.RegisterRequest;
import com.basak.identity_service.DTO.UserDTO;
import com.basak.identity_service.Service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public UserDTO register(@RequestBody RegisterRequest request) {
        return userService.register(request);
    }


}
