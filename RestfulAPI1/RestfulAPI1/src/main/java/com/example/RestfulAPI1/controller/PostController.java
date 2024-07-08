package com.example.RestfulAPI1.controller;

import com.example.RestfulAPI1.model.Post;
import com.example.RestfulAPI1.service.Post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")

@RestController
    @RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/getAllPosts")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/getByUser/{userId}")
    public ResponseEntity<List<Post>> getPostsByUserId(@PathVariable String userId){
        List<Post> posts = postService.getPostByUser(userId);

        return ResponseEntity.ok(posts);

    }

    @PostMapping("/add")
    public ResponseEntity<String> addPost(@RequestParam String userId, @RequestParam String content) {
        postService.addPost(userId, content);
        return ResponseEntity.ok("Post added successfully");
    }


}
