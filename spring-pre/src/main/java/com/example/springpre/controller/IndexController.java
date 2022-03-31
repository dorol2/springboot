package com.example.springpre.controller;

import com.example.springpre.config.auth.LoginUser;
import com.example.springpre.config.auth.dto.SessionUser;
import com.example.springpre.dto.posts.PostsDto;
import com.example.springpre.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());

        // SessionUser user = (SessionUser) httpSession.getAttribute("user"); // 로그인 성공 시, 세션에 SessionUser 저장
        if(user != null) {
            model.addAttribute("userName", user.getName());
        }

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postUpdate(@PathVariable Long id, Model model) {
        PostsDto postsDto = postsService.findById(id);
        model.addAttribute("post", postsDto);

        return "posts-update";
    }
}
