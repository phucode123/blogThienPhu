package com.example.RestfulAPI1.controller;

import com.example.RestfulAPI1.model.User;
import com.example.RestfulAPI1.service.UserService;
import com.example.RestfulAPI1.token.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/login")
    public ResponseEntity<?> handleLogin(@RequestBody Map<String, String> loginRequest) {
        String username = loginRequest.get("username");
        String password = loginRequest.get("password");

        System.out.println(username+" "+password);
        if (userService.authenticate(username, password)) {
            User user = userService.findByUsername(username);
            System.out.println("login: "+ user);
            String token = jwtUtil.generateToken(username);

            // Gửi token và thông tin người dùng về cho frontend
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("data", user);

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Invalid username or password"));
        }
    }
}
