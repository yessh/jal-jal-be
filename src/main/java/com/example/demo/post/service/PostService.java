package com.example.demo.post.service;

import com.example.demo.global.exception.BusinessException;
import com.example.demo.global.response.ApiStatus;
import com.example.demo.member.domain.entity.Member;
import com.example.demo.post.domain.entity.Post;
import com.example.demo.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;

    public Post createPost(String title, String content, Member author) {
        Post post = Post.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
        return postRepository.save(post);
    }

    public Post updatePost(Long postId, String title, String content, Member author) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new BusinessException(ApiStatus.EXCEPTION_OCCUR));
        if (!post.getAuthor().equals(author)) {
            throw new BusinessException(ApiStatus.MEMBER_NOT_FOUND);
        }
        post.update(title, content);
        return post;
    }

    @Transactional
    public void deletePost(Long postId, Member author) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new BusinessException(ApiStatus.POST_NOT_FOUND));
        if (!post.getAuthor().equals(author)) {
            throw new BusinessException(ApiStatus.POST_NOT_FOUND);
        }
        postRepository.delete(post);
    }
}
