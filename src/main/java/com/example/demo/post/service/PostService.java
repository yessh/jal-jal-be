package com.example.demo.post.service;

import com.example.demo.global.exception.BusinessException;
import com.example.demo.global.response.ApiStatus;
import com.example.demo.hashtag.domain.Hashtag;
import com.example.demo.hashtag.domain.PostHashtag;
import com.example.demo.hashtag.repository.HashtagRepository;
import com.example.demo.hashtag.repository.PostHashtagRepository;
import com.example.demo.member.domain.entity.Member;
import com.example.demo.post.domain.entity.Post;
import com.example.demo.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final HashtagRepository hashtagRepository;
    private final PostHashtagRepository postHashtagRepository;

    public Post createPost(String title, String content, Member author,
                           Set<String> reqHashtags) {
        Post post = Post.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();

        /**
         * 해시태그 부분
         */
        for (String hashtagName : reqHashtags) {
            Hashtag hashtag = hashtagRepository.findByHashtagName(hashtagName)
                    .orElseGet(() -> {
                        Hashtag newHashtag = new Hashtag(hashtagName);
                        return hashtagRepository.save(newHashtag);
                    });
            PostHashtag postHashtag = new PostHashtag(post, hashtag);
            post.addPostHashtag(postHashtag);
        }

        return postRepository.save(post);
    }

    public Post updatePost(Long postId, String title, String content, Member author, Set<String> reqHashtags) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new BusinessException(ApiStatus.EXCEPTION_OCCUR));
        if (!post.getAuthor().equals(author)) {
            throw new BusinessException(ApiStatus.MEMBER_NOT_FOUND);
        }

        post.update(title, content);

        /**
         * 해시태그 추가,삭제
         */
        Set<PostHashtag> existingPostHashtags = post.getPostHashtags();
        Set<String> existingHashtagNames = existingPostHashtags.stream()
                .map(ph -> ph.getHashtag().getHashtagName())
                .collect(Collectors.toSet());

        Set<String> newHashtagNames = new HashSet<>(reqHashtags);

        Set<String> hashtagsToAdd = new HashSet<>(newHashtagNames);
        hashtagsToAdd.removeAll(existingHashtagNames);

        Set<String> hashtagsToRemove = new HashSet<>(existingHashtagNames);
        hashtagsToRemove.removeAll(newHashtagNames);

        for (String hashtagName : hashtagsToAdd) {
            Hashtag hashtag = hashtagRepository.findByHashtagName(hashtagName)
                    .orElseGet(() -> hashtagRepository.save(new Hashtag(hashtagName)));
            PostHashtag postHashtag = new PostHashtag(post, hashtag);
            post.addPostHashtag(postHashtag);
        }

        for (String hashtagName : hashtagsToRemove) {
            existingPostHashtags.removeIf(postHashtag -> {
                if(postHashtag.getHashtag().getHashtagName().equals(hashtagName)) {
                    postHashtagRepository.delete(postHashtag);
                    return true;
                }
                return false;
            });
        }
        Post updatedPost = postRepository.save(post);
        return updatedPost;
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
