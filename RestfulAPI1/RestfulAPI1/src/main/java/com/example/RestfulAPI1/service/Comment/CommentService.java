package com.example.RestfulAPI1.service.Comment;

import com.example.RestfulAPI1.Repository.CommentRepository;
import com.example.RestfulAPI1.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public void addComment(String userId, String postId, String content){
        String commentId = UUID.randomUUID().toString(); // Tạo UUID cho postId
        Timestamp createdAt = new Timestamp(System.currentTimeMillis()); // Lấy thời gian hiện tại
        commentRepository.addComment(commentId, userId, postId,content, createdAt);
    }

    public List<Comment> getCommentsByUsersWhoCommentedOnPost(String postId) {
        List<Comment> comments = commentRepository.findCommentsByUsersWhoCommentedOnPost(postId);//... lấy dữ liệu từ database
        System.out.println("Comments: " + comments.size()); // In ra log để kiểm tra
        return comments;
    }

    public List<String> getUserIdsByPostId(String postId){
        return commentRepository.findUserIdsByPostId(postId);
    }

}
