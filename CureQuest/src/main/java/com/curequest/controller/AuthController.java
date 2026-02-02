package com.curequest.controller;

import com.curequest.dto.*;
import com.curequest.entity.User;
import com.curequest.repository.UserRepository;
import com.curequest.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired private UserRepository userRepo;
    @Autowired private PasswordEncoder encoder;
    @Autowired private JwtUtil jwtUtil;

    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequest req) {

        if (userRepo.existsByUsername(req.getUsername())) {
            return "Username already exists";
        }

        if (userRepo.existsByEmail(req.getEmail())) {
            return "Email already exists";
        }

        User u = new User();
        u.setUsername(req.getUsername());
        u.setEmail(req.getEmail());
        u.setPassword(encoder.encode(req.getPassword()));
        u.setRole("ROLE_" + req.getRole()); // e.g., USER -> ROLE_USER

        userRepo.save(u);

        return "Signup successful";
    }


    @PostMapping("/login")
    public String login(@RequestBody LoginRequest req) {
        User u = userRepo.findByUsername(req.getUsername());
        if (u != null && encoder.matches(req.getPassword(), u.getPassword())) {
            return jwtUtil.generateToken(u.getUsername());
        }
        return "Invalid credentials";
    }
}