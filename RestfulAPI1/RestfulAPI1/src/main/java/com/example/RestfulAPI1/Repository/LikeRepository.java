package com.example.RestfulAPI1.Repository;

import com.example.RestfulAPI1.model.Like;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LikeRepository extends JpaRepository<Like, String> {
    @Query("SELECT l.user.id FROM Like l WHERE l.post.id = :postId")
    List<String> findUserIdsByPostId(String postId);

    //thêm like
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO likes (like_id, user_id, post_id) VALUES (:likeId, :userId, :postId)", nativeQuery = true)
    void addLike(@Param("likeId") String likeId, @Param("userId") String userId, @Param("postId") String postId);

    //xoá like
    @Modifying
    @Transactional
    @Query("DELETE FROM Like l WHERE l.user.id = :userId AND l.post.id = :postId")
    void deleteLikeByUserIdAndPostId(@Param("userId") String userId, @Param("postId") String postId);
}
