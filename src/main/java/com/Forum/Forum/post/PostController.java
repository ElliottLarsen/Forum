package com.Forum.Forum.post;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class PostController {
    private final PostRepository postRepository;

    @GetMapping("/post/list")
    @ResponseBody
    public List<Post> getPostList() {
        List<Post> postList = this.postRepository.findAll();
        return postList;
    }
}
