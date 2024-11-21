package com.example.demo.post.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostRequestDto {

    @NotNull
    private String title;

    @NotNull
    private String content;

    private List<String> hashtags;
}
