package com.example.springpre.repository;

import com.example.springpre.entity.posts.PostsEntity;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@SpringBootTest // H2 사용
class PostsRepositoryTest {

    @Autowired
    private PostsRepository postsRepository;

    @After("")
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void savePostsAndOpenPosts() throws Exception {
        String title = "testposts";
        String content = "testcontents";

        var test = postsRepository.save(PostsEntity.builder()
                .title(title)
                .content(content)
                .author("me")
                .build());

        PostsEntity postsEntity = postsRepository.findById(test.getId())
                .orElseThrow(() -> new UsernameNotFoundException(test.getId() + "is not found"));

        assertThat(postsEntity.getTitle()).isEqualTo(title);
        assertThat(postsEntity.getContent()).isEqualTo(content);
    }

    @Test
    public void saveBaseTimeEntity() {
        //given
        LocalDateTime now = LocalDateTime.now();
        postsRepository.save(PostsEntity.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());
        //when
        List<PostsEntity> postsList = postsRepository.findAll();

        //then
        PostsEntity posts = postsList.get(0);

        System.out.println(">>>>>>>>> createDate=" + posts.getCreatedDate() + ", modifiedDate=" + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}