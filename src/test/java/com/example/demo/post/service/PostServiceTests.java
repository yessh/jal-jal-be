package com.example.demo.post.service;

import com.example.demo.member.domain.entity.Member;
import com.example.demo.post.domain.entity.Post;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.member.domain.entity.Role;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PostServiceTests {

    @Autowired
    private PostService postService;

    @Test
    void createPostTest() {
        // Given
        Member author = Member.builder()
                .id(1L)
                .email("test@example.com")
                .role(Role.MEMBER)
                .build();
        String title = "Test Title";
        String content = "Test Content";

        // When
        Post post = postService.createPost(title, content, author);

        // Then
        assertNotNull(post.getId());
    }
}