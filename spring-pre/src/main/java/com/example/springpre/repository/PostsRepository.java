package com.example.springpre.repository;

import com.example.springpre.entity.posts.PostsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<PostsEntity, Long> {


    @Query("select p from PostsEntity p order by p.id desc")
    List<PostsEntity> findAllDesc();

}
