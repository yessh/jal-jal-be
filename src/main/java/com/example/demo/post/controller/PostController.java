package com.example.demo.post.controller;

import com.example.demo.global.response.ApiResponse;
import com.example.demo.member.domain.entity.Member;
import com.example.demo.post.domain.entity.Post;
import com.example.demo.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ApiResponse<Post> createPost(@RequestParam String title,
                                        @RequestParam String content,
                                        @RequestParam Member author,
                                        @RequestParam Set<String> hashtags) {
        Post post = postService.createPost(title, content, author, hashtags);
        return ApiResponse.ok(post);
    }

    @PutMapping("/{postId}")
    public ApiResponse<Post> updatePost(@PathVariable Long postId,
                                        @RequestParam String title,
                                        @RequestParam String content,
                                        @RequestParam Member author,
                                        @RequestParam Set<String> hashtags) {
        Post post = postService.updatePost(postId, title, content, author, hashtags);
        return ApiResponse.ok(post);
    }

    @DeleteMapping("/{postId}")
    public ApiResponse<Void> deletePost(@PathVariable Long postId,
                                        @RequestParam Member author) {
        postService.deletePost(postId, author);
        return ApiResponse.ok();
    }
}
