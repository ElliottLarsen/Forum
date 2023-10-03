package com.Forum.Forum.post;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class PostController {
    private final PostService postService;

    @GetMapping("/post/list")
    @ResponseBody
    public List<Post> postList() {
        List<Post> postList = this.postService.getPostList();
        return postList;
    }

    @GetMapping(value = "/post/detail/{id}")
    @ResponseBody
    public Post postDetail(@PathVariable("id") Integer id) {
        Post post = this.postService.getPost(id);
        return post;
    }

}
