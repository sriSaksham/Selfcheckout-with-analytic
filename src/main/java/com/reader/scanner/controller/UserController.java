package com.reader.scanner.controller;

import com.reader.scanner.model.User;
import com.reader.scanner.repository.UserRepository;
import com.reader.scanner.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already in use");
        }

        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already in use");
        }

        // Hash the password before saving the user
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(user);

        // Send registration email
        String subject = "Registration Successful";
        String body = "Dear " + user.getUsername() + ",\n\nYou have successfully registered.\n\nRegards,\nYour Company";
        emailService.sendRegistrationEmail(user.getEmail(), subject, body);

        return ResponseEntity.ok(savedUser);  // Return the saved user object including the generated userId
    }
}
