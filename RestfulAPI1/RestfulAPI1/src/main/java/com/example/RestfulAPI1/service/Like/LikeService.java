package com.example.RestfulAPI1.service.Like;

import com.example.RestfulAPI1.Repository.LikeRepository;
import com.example.RestfulAPI1.Repository.PostRepository;
import com.example.RestfulAPI1.Repository.UserRepository;
import com.example.RestfulAPI1.model.Like;
import com.example.RestfulAPI1.model.Post;
import com.example.RestfulAPI1.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;
    public List<String> getUserIdsByPostId(String postId) {
        return likeRepository.findUserIdsByPostId(postId);
    }
    public void addLike(String userIdStr, String postIdStr) {
        if ( userIdStr != null && postIdStr != null) {
            likeRepository.addLike(UUID.randomUUID().toString(), userIdStr, postIdStr);
        } else {
            System.out.println("cai gi do bi null");
            // Handle invalid UUID strings
        }
        //likeRepository.save(like);
    }

    public void deleteLikeByUserIdAndPostId(String userId, String postId) {
        likeRepository.deleteLikeByUserIdAndPostId(userId, postId);
    }
}
