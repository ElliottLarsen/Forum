package com.Forum.Forum.comment;

import com.Forum.Forum.post.Post;
import com.Forum.Forum.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
public class CommentController {
    private final PostService postService;
    private final CommentService commentService;

    @PostMapping(value = "comment/create/{id}")
    @ResponseBody
    public Post createComment(@PathVariable("id") Integer id, @RequestParam String content) {
        Post post = this.postService.getPost(id);
        this.commentService.create(post, content);
        Post updated_post = this.postService.getPost(id);
        return updated_post;
    }
}
