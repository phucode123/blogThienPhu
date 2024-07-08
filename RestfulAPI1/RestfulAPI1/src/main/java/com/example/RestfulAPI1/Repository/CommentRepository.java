package com.example.RestfulAPI1.Repository;

import com.example.RestfulAPI1.model.Comment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {
    @Query("SELECT l.user.id FROM Comment l WHERE l.post.id = :postId")
    List<String> findUserIdsByPostId(String postId);

    @Query(value = "SELECT * FROM comments c WHERE c.post_id = :postId", nativeQuery = true)
    List<Comment> findCommentsByUsersWhoCommentedOnPost(@Param("postId") String postId);

    //thêm comment
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO comments (comment_id, user_id, post_id,content, created_at) VALUES (:commentId, :userId, :postId,:content, :createdAt)", nativeQuery = true)
    void addComment(@Param("commentId") String commentId, @Param("userId") String userId, @Param("postId") String postId ,@Param("content") String content, @Param("createdAt")Timestamp createdAt);
}
