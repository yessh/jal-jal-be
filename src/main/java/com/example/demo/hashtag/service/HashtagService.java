package com.example.demo.hashtag.service;

import com.example.demo.hashtag.domain.Hashtag;
import com.example.demo.hashtag.domain.PostHashtag;
import com.example.demo.hashtag.repository.PostHashtagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HashtagService {

    private final PostHashtagRepository postHashtagRepository;

    public List<Hashtag> getPopularHashtags(){
        List<PostHashtag> postHashtags = postHashtagRepository.findAll();
        Map<Hashtag, Long> hashtagCountMap = new HashMap<>();

        for (PostHashtag postHashtag : postHashtags) {
            Hashtag hashtag = postHashtag.getHashtag();
            hashtagCountMap.put(hashtag, hashtagCountMap.getOrDefault(hashtag, 0L) + 1);
        }

        List<Hashtag> popularHashtags = hashtagCountMap.entrySet().stream()
                .sorted((entry1, entry2) -> Long.compare(entry2.getValue(), entry1.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        return hashtagCountMap.entrySet().stream()
                .sorted((entry1, entry2) -> Long.compare(entry2.getValue(), entry1.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
