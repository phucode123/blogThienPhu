package com.example.RestfulAPI1.service;

import com.example.RestfulAPI1.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserServiceInterface {
    public List<User> getListUser();
    public User createUser(User user);
    public void deleteUser(String id);
    public boolean authenticate(String username, String password);
    public User findByUsername(String username);
    public User getUserById(String userId);

}
