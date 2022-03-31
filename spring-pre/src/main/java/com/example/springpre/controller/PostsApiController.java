package com.example.springpre.controller;

import com.example.springpre.dto.posts.PostsDto;
import com.example.springpre.dto.posts.PostsSaveRequestDto;
import com.example.springpre.dto.posts.PostsUpdateRequestDto;
import com.example.springpre.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PostsApiController {

    private final PostsService postsService;

    @GetMapping("api/v1/testposts")
    public Long insertDummyData() {
        Long tempID = 1L;

        for (int i = 0; i < 10; ++i) {
            PostsSaveRequestDto postsDto = new PostsSaveRequestDto("testTitle" + i, "testContents", "testAuthor");

            tempID = postsService.save(postsDto);
        }

        return tempID;
    }

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto postsSaveRequestDto) {
        return postsService.save(postsSaveRequestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }

}
