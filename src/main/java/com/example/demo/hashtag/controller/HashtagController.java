package com.example.demo.hashtag.controller;

import com.example.demo.hashtag.domain.Hashtag;
import com.example.demo.hashtag.service.HashtagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class HashtagController {

    private final HashtagService hashtagService;

    @GetMapping("/api/hashtags")
    public ResponseEntity<List<Hashtag>> getPopularHashtags(){
        List<Hashtag> popularHashtags = hashtagService.getPopularHashtags();
        if(popularHashtags.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(popularHashtags);
    }

}