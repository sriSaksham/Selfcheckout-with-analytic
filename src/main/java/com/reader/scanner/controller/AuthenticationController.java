package com.reader.scanner.controller;

import com.reader.scanner.model.User;
import com.reader.scanner.service.AuthenticationService;
import com.reader.scanner.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        try {
            User user = authenticationService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
            String token = jwtUtil.generateToken(user.getEmail());
            String bearerToken = "Bearer " + token;
            Map<String, String> response = new HashMap<>();
            response.put("token", bearerToken);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }
}

// added 28th line
//changes at 30th line with bearertoken


// Assuming 'token' is retrieved from the login response and stored securely (e.g., in local storage)
//const token = localStorage.getItem('jwtToken');
//const bearerToken = `Bearer ${token}`; // Prepend "Bearer "
//
//fetch('http://localhost:8080/api/your-protected-endpoint', {
//    method: 'GET',
//            headers: {
//        'Authorization': bearerToken, // Set the Authorization header
//                'Content-Type': 'application/json'
//    }
//}).then(response => {
//    if (response.ok) {
//        return response.json();
//    }
//    throw new Error('Network response was not ok.');
//}).then(data => {
//    console.log(data);
//}).catch(error => {
//        console.error('There was a problem with your fetch operation:', error);
//});