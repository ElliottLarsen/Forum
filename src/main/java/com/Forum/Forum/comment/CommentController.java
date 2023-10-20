package com.Forum.Forum.comment;

import com.Forum.Forum.post.Post;
import com.Forum.Forum.post.PostService;
import com.Forum.Forum.user.SiteUser;
import com.Forum.Forum.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.ui.Model;
import java.security.Principal;
import org.springframework.security.access.prepost.PreAuthorize;

@RequiredArgsConstructor
@Controller
public class CommentController {
    private final PostService postService;
    private final CommentService commentService;
    private final UserService userService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("comment/create/{id}")
    public String createComment(Model model, @PathVariable("id") Integer id,
                                @Valid CommentCreateForm commentCreateForm, BindingResult bindingResult, Principal principal) {
        Post post = this.postService.getPost(id);
        SiteUser siteUser = this.userService.getUser(principal.getName());
        if (bindingResult.hasErrors()) {
            model.addAttribute("post", post);
            return "post_detail";
        }
        this.commentService.create(post, commentCreateForm.getContent(), siteUser);
        return String.format("redirect:/post/detail/%s", id);
    }
}
