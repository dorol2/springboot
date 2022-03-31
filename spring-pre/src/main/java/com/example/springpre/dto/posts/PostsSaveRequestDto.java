package com.example.springpre.dto.posts;

import com.example.springpre.entity.posts.PostsEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public PostsEntity toEntity() {
        return PostsEntity.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
