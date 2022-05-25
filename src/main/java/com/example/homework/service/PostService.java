package com.example.homework.service;

import com.example.homework.domain.Post;
import com.example.homework.domain.PostRepository;
import com.example.homework.domain.PostRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.security.auth.login.FailedLoginException;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    public Post find(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new NullPointerException("아이디가 존재하지 않습니다.")
        );
        return post;
    }

    public List<Post> findAll(){
        LocalDateTime start = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.of(0,0,0));
        LocalDateTime end = LocalDateTime.of(LocalDate.now(), LocalTime.of(23,59,59));
        return postRepository.findAllByModifiedAtBetweenOrderByModifiedAtDesc(start, end);
    }

    @Transactional
    public Post createPost(PostRequestDto requestDto){
        Post post = new Post(requestDto);
        return postRepository.save(post);
    }

    @Transactional
    public Long update(Long id, PostRequestDto requestDto) {
        try {
            Post post = postRepository.findById(id).orElseThrow(
                    () -> new NullPointerException("아이디가 존재하지 않습니다.")
            );

            if (!post.getPassword().equals(requestDto.getPassword())) {
                throw new FailedLoginException("비밀번호가 일치하지 않습니다.");
            } else {
                post.update(requestDto);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return id;
    }

    @Transactional
    public Long remove(Long id, PostRequestDto requestDto){
        try {
            Post post = postRepository.findById(id).orElseThrow(
                    () -> new NullPointerException("아이디가 존재하지 않습니다.")
            );

            if (!post.getPassword().equals(requestDto.getPassword())) {
                throw new FailedLoginException("비밀번호가 일치하지 않습니다.");
            } else {
                postRepository.delete(post);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return id;
    }
}
