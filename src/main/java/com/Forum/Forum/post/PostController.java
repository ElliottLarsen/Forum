package com.Forum.Forum.post;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class PostController {
    private final PostService postService;

    // TODO: Create PostMapping for posts.

    @GetMapping("/post/list")
    @ResponseBody
    public Page<Post> postList(@RequestParam(value = "page", defaultValue = "0") int page) {
        Page<Post> paging = this.postService.getPostList(page);
        return paging;
    }

    @GetMapping(value = "/post/detail/{id}")
    @ResponseBody
    public Post postDetail(@PathVariable("id") Integer id) {
        Post post = this.postService.getPost(id);
        return post;
    }

    @PostMapping("/modify/{id}")
    @ResponseBody
    public Post postModify(PostUpdateForm postUpdateForm, @PathVariable("id") Integer id) {
        Post post = this.postService.getPost(id);
        this.postService.modifyPost(post, postUpdateForm.getSubject(), postUpdateForm.getContent());
        return this.postService.getPost(id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public String postDelete(@PathVariable("id") Integer id) {
        Post post = this.postService.getPost(id);
        this.postService.delete(post);
        return "Delete success.";
    }

}
