package com.example.RestfulAPI1.Repository;

import com.example.RestfulAPI1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    // Custom query to find a user by username
    Optional<User> findByUsername(String username);

    @Query(value = "SELECT * FROM users c WHERE c.id = :userId", nativeQuery = true)
    User findByUserId(String userId);
}
