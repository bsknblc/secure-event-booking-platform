package com.basak.identity_service.Controller;

import com.basak.identity_service.DTO.RegisterRequest;
import com.basak.identity_service.DTO.UserDTO;
import com.basak.identity_service.Service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserDTO register(@RequestBody RegisterRequest request) {
        return userService.register(request);
    }

}
