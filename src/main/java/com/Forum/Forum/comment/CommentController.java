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
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ResponseStatusException;
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

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/comment/modify/{id}")
    public String commentModify(CommentCreateForm commentForm, @PathVariable("id") Integer id, Principal principal) {
        Comment comment = this.commentService.getComment(id);
        if (!comment.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You are not authorized to edit this.");
        }
        commentForm.setContent(comment.getContent());
        return "comment_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/comment/modify/{id}")
    public String commentModify(@Valid CommentCreateForm commentForm, BindingResult bindingResult, @PathVariable("id") Integer id, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "comment_form";
        }
        Comment comment = this.commentService.getComment(id);
        if (!comment.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You are not authorized to edit this.");
        }
        this.commentService.modify(comment, commentForm.getContent());
        return String.format("redirect:/post/detail/%s", comment.getPost().getId());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/comment/delete/{id}")
    public String commentDelete(Principal principal, @PathVariable("id") Integer id) {
        Comment comment = this.commentService.getComment(id);
        if (!comment.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You are not authorized to delete this.");
        }
        this.commentService.delete(comment);
        return String.format("redirect:/post/detail/%s", comment.getPost().getId());
    }
}
