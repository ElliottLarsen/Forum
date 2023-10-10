package com.Forum.Forum.comment;

import com.Forum.Forum.post.Post;
import com.Forum.Forum.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.ui.Model;

@RequiredArgsConstructor
@Controller
public class CommentController {
    private final PostService postService;
    private final CommentService commentService;

    @PostMapping("comment/create/{id}")
    public String createComment(Model model, @PathVariable("id") Integer id,
                                @Valid CommentCreateForm commentCreateForm, BindingResult bindingResult) {
        Post post = this.postService.getPost(id);
        if (bindingResult.hasErrors()) {
            model.addAttribute("post", post);
            return "post_detail";
        }
        this.commentService.create(post, commentCreateForm.getContent());
        return String.format("redirect:/post/detail/%s", id);
    }
}
