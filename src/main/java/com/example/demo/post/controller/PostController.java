package com.example.demo.post.controller;

import com.example.demo.post.domain.dto.PostRequestDto;
import com.example.demo.post.domain.dto.PostResponseDto;
import com.example.demo.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/api/post/create")
    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostRequestDto postRequestDto, Long memberId) {
        PostResponseDto postResponseDto = postService.createPost(postRequestDto, memberId);
        return ResponseEntity.ok(postResponseDto);
    }
}
