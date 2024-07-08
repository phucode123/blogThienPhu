package com.example.RestfulAPI1.service.Post;

import com.example.RestfulAPI1.model.Post;

import java.sql.Timestamp;
import java.util.List;

public interface PostInterface {
    public List<Post> getAllPosts();

    public void addPost(String userId, String content);

    public  List<Post> getPostByUser(String userId);

}
