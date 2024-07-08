package com.example.RestfulAPI1.controller;

import com.example.RestfulAPI1.service.Like.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/likes")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<String>> getUserIdsByPostId(@PathVariable String postId) {
        List<String> userIds = likeService.getUserIdsByPostId(postId);
        return ResponseEntity.ok(userIds);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addLike(@RequestParam String userId, @RequestParam String postId) {
        System.out.println("userId: "+ userId);
        System.out.println("\npostId: "+ postId);

        likeService.addLike(userId, postId);
        return ResponseEntity.ok("Like added successfully");
    }

    //xoá like
    @DeleteMapping("/delete")
//    public ResponseEntity<String> deleteLike(@RequestParam String userId, @RequestParam String postId) {
//        System.out.println("userId: "+ userId);
//        System.out.println("\npostId: "+ postId);
//        likeService.deleteLikeByUserIdAndPostId(userId, postId);
//        return ResponseEntity.ok("Like deleted successfully");
//    }
    public ResponseEntity<String> removeLike(@RequestParam String userId, @RequestParam String postId) {
        try {
            System.out.println("userId: "+ userId);
            System.out.println("\npostId: "+ postId);
            likeService.deleteLikeByUserIdAndPostId(userId, postId);
            return ResponseEntity.ok("Like removed successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error removing like: " + e.getMessage());
        }
    }
}
