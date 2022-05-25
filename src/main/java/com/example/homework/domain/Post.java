package com.example.homework.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@NoArgsConstructor      // 기본 생성자를 만든다.
@Getter
@Entity                 // 테이블과 연계됨을 스프링에게 알려준다.
public class Post extends Timestamped{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String writer;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String contents;

    public Post(String title, String writer, String password, String contents) {
        this.title = title;
        this.writer = writer;
        this.password = password;
        this.contents = contents;
    }

    public Post(PostRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.writer = requestDto.getWriter();
        this.contents = requestDto.getContent();
        this.password = requestDto.getPassword();
    }

    public void update(PostRequestDto requestDto){
        this.contents = requestDto.getContent();
    }
}
