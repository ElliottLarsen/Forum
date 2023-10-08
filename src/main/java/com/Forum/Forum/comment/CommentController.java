package com.Forum.Forum.comment;

import com.Forum.Forum.post.Post;
import com.Forum.Forum.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;

@RequiredArgsConstructor
@Controller
public class CommentController {
    private final PostService postService;
    private final CommentService commentService;

    @PostMapping("comment/create/{id}")
    public String createComment(Model model, @PathVariable("id") Integer id, @RequestParam String content) {
        Post post = this.postService.getPost(id);
        this.commentService.create(post, content);
        return String.format("redirect:/post/detail/%s", id);
    }
}
