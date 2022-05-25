package com.example.homework.domain;

import lombok.Getter;

@Getter
public class PostRequestDto {
    private String writer;
    private String title;
    private String password;
    private String content;
}
