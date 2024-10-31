package com.crio.stayEase.controller;

import com.crio.stayEase.config.JwtUtil;
import com.crio.stayEase.dto.CreateUser;
import com.crio.stayEase.dto.JwtRequest;
import com.crio.stayEase.dto.JwtResponse;
import com.crio.stayEase.entity.User;
import com.crio.stayEase.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.crio.stayEase.config.PathConstants.LOGIN_USER;
import static com.crio.stayEase.config.PathConstants.REGISTER_USER;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @PostMapping(LOGIN_USER)
    public JwtResponse login(@Valid @RequestBody JwtRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        String token = jwtUtil.generateToken(request.getUsername());

        return new JwtResponse(token);
    }

    @PostMapping(REGISTER_USER)
    public ResponseEntity<User> registerUser(@Valid @RequestBody CreateUser createUser) {
        User user = userService.registerUser(createUser);

        return ResponseEntity.ok(user);
    }
}
