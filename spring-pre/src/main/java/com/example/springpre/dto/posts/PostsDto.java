package com.example.springpre.dto.posts;

import com.example.springpre.entity.posts.PostsEntity;
import com.sun.istack.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class PostsDto {

    @NotNull
    private Long id;
    private String title;
    private String author;
    private String content;
    @NotNull
    private LocalDateTime modifiedDate;

    public PostsDto(PostsEntity postsEntity) {
        this.id = postsEntity.getId();
        this.title = postsEntity.getTitle();
        this.author = postsEntity.getAuthor();
        this.content = postsEntity.getContent();
        this.modifiedDate = postsEntity.getModifiedDate();
    }

    @Builder
    public PostsDto(String title, String content, String author) {
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
