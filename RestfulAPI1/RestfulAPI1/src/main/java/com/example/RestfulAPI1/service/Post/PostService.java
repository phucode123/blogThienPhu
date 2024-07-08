package com.example.RestfulAPI1.service.Post;

import com.example.RestfulAPI1.Repository.PostRepository;
import com.example.RestfulAPI1.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
public class PostService implements PostInterface {
    @Autowired
    private PostRepository postRepository;


    @Override
    public List<Post> getAllPosts() {
        List<Post> datas = postRepository.findAll();
        return datas;
    }
    public void addPost(String userId, String content) {
        String postId = UUID.randomUUID().toString(); // Tạo UUID cho postId
        Timestamp createdAt = new Timestamp(System.currentTimeMillis()); // Lấy thời gian hiện tại

        postRepository.addPost(postId, userId, content, createdAt);
    }

    @Override
    public List<Post> getPostByUser(String userId) {
        List<Post> datas = postRepository.findByIdUser(userId);
        return datas;
    }

}
