package com.example.RestfulAPI1.Repository;

import com.example.RestfulAPI1.model.Post;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO posts (post_id, user_id, content, created_at) VALUES (:postId, :userId, :content, :createdAt)", nativeQuery = true)
    void addPost(@Param("postId") String postId, @Param("userId") String userId, @Param("content") String content,@Param("createdAt")Timestamp createdAt);
    @Query(value = "SELECT * FROM posts c WHERE c.user_id = :userId", nativeQuery = true)
    List<Post> findByIdUser(String userId);
}
