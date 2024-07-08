package com.example.RestfulAPI1.controller;

import com.example.RestfulAPI1.Repository.UserRepository;
import com.example.RestfulAPI1.model.User;
import com.example.RestfulAPI1.service.UserService;
import com.example.RestfulAPI1.token.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/getAllusers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        System.out.println("hellooo:  "+users);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id) {
        User user = userRepository.findById(id).orElse(null);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with id: " + id);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Đã lấy được user");
            response.put("data", user);
            return ResponseEntity.ok(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletedUser(@PathVariable String id, @RequestHeader("Authorization") String token) {
        String username = jwtUtil.extractUsername(token);
        User requestingUser = userRepository.findByUsername(username).orElse(null);

        if (requestingUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token or user not found");
        }

        if ("ADMIN".equals(requestingUser.getRole()) && !requestingUser.getId().equals(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            if (requestingUser.getId().equals(id)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Ban khong the xoa chinh minh");
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Ban khong co quyen xoa nguoi dung");
            }
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            Map<String, Object> response = new HashMap<>();
            User createdUser = userService.createUser(user);
            response.put("message", "OK");
            response.put("data", createdUser);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
        }
    }
}
