package com.example.homework.controller;

import com.example.homework.domain.Post;
import com.example.homework.domain.PostRepository;
import com.example.homework.domain.PostRequestDto;
import com.example.homework.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import javax.xml.bind.SchemaOutputResolver;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostService postService;

    @GetMapping("/api/post")
    public Post getPost(@RequestParam Long id) {

        return postService.find(id);
    }

    @GetMapping("/api/posts")
    public List<Post> getListPost() {
        return postService.findAll();
    }

    @PostMapping("/api/posts")
    public Post createPost(@RequestBody PostRequestDto requestDto){ return postService.createPost(requestDto); }

    @PutMapping("/api/posts/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) { return postService.update(id, requestDto); }

    @DeleteMapping("/api/posts/{id}")
    public Long deletePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) { return postService.remove(id, requestDto); }
}
