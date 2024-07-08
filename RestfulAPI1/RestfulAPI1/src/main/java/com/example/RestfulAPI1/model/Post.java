package com.example.RestfulAPI1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @Column(name = "post_id", nullable = false, length = 36)
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String postId = UUID.randomUUID().toString();  // Tạo giá trị UUID ngẫu nhiên cho post_id

    @Column(name = "content", nullable = false, length = 1000)  // Giới hạn độ dài nội dung
    private String content;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());

    @Column(name = "user_id", nullable = false, length = 36)
    private String userId;  // ID của người dùng tạo bài đăng

    // Quan hệ nhiều bài đăng có thể thuộc về một người dùng
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
//    @JsonIgnore
    private User user;
}
