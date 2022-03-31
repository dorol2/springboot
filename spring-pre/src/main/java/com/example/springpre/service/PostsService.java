package com.example.springpre.service;

import com.example.springpre.dto.posts.PostsDto;
import com.example.springpre.dto.posts.PostsSaveRequestDto;
import com.example.springpre.dto.posts.PostsUpdateRequestDto;
import com.example.springpre.entity.posts.PostsEntity;
import com.example.springpre.repository.PostsRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
@Transactional
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        PostsEntity postsEntity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        postsEntity.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsDto findById(Long id) {
        PostsEntity postsEntity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new PostsDto(postsEntity);
    }

    public List<PostsDto> findAllDesc() {
        return postsRepository.findAllDesc()
                .stream()
                .map(PostsDto::new)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        PostsEntity postsEntity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id = " + id));

        postsRepository.delete(postsEntity);
    }
}
