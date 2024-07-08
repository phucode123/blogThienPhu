package com.example.RestfulAPI1.controller;

import com.example.RestfulAPI1.model.Comment;
import com.example.RestfulAPI1.service.Comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

//    @GetMapping("/add/{postId}")
//    public ResponseEntity<?> getCommentsByPostId(@PathVariable String postId){
//        return null;
//    }
    @GetMapping("/post/{postId}")
    public ResponseEntity<?> getUserCommentByPostId(@PathVariable String postId){
        List<String> data = commentService.getUserIdsByPostId(postId);
        return ResponseEntity.ok(data);
    }
    @PostMapping("/add")
    public ResponseEntity<String> addComment(@RequestParam String userId, @RequestParam String postId , @RequestParam String content) {
        System.out.println("userId: "+ userId);
        System.out.println("\npostId: "+ postId);
        System.out.println("\n content: "+ content);

        commentService.addComment(userId, postId, content);
        return ResponseEntity.ok("Comment added successfully");
    }

    @GetMapping("/post/{postId}/users-comments")
    public ResponseEntity<List<Comment>> getCommentsByUsersWhoCommentedOnPost(@PathVariable String postId) {
        List<Comment> comments = commentService.getCommentsByUsersWhoCommentedOnPost(postId);
        return ResponseEntity.ok(comments);
    }
}
