package com.example.demo.post.service;

import com.example.demo.global.exception.BusinessException;
import com.example.demo.global.response.ApiStatus;
import com.example.demo.hashtag.domain.Hashtag;
import com.example.demo.hashtag.domain.PostHashtag;
import com.example.demo.hashtag.repository.HashtagRepository;
import com.example.demo.hashtag.repository.PostHashtagRepository;
import com.example.demo.member.domain.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.post.domain.Post;
import com.example.demo.post.domain.dto.PostRequestDto;
import com.example.demo.post.domain.dto.PostResponseDto;
import com.example.demo.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private PostRepository postRepository;
    private HashtagRepository hashtagRepository;
    private PostHashtagRepository postHashtagRepository;
    private MemberRepository memberRepository;

    public PostResponseDto createPost(PostRequestDto postRequestDto,
                                      Long memberId) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessException(ApiStatus.MEMBER_NOT_FOUND));

        Post post = new Post(postRequestDto);

        /**
         * 해시태그 부분
         */
        Set<PostHashtag> postHashtags = new HashSet<>();
        for (String hashtagName : postRequestDto.getHashtags()) {
            Hashtag hashtag = hashtagRepository.findByHashtagName(hashtagName)
                    .orElseGet(() -> {
                        Hashtag newHashtag = new Hashtag(hashtagName);
                        return hashtagRepository.save(newHashtag);
                    });

            PostHashtag postHashtag = new PostHashtag(post, hashtag);
            post.addPostHashtag(postHashtag);
        }

        //settter 없이 구현 How ?
        post = postRepository.save(post);

        return new PostResponseDto(post);
    }
}
