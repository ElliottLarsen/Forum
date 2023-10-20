package com.Forum.Forum.post;

import java.util.List;
import com.Forum.Forum.comment.CommentCreateForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import java.security.Principal;
import com.Forum.Forum.user.SiteUser;
import com.Forum.Forum.user.UserService;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class PostController {
    private final PostService postService;
    private final UserService userService;


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/post/create")
    public String postCreate(PostCreateForm postCreateForm) {
        return "post_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/post/create")
    public String postCreate(@Valid PostCreateForm postCreateForm, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "post_form";
        }
        SiteUser siteUser = this.userService.getUser(principal.getName());
        this.postService.createPost(postCreateForm.getSubject(), postCreateForm.getContent(), siteUser);
        return "redirect:/post/list";
    }

    @GetMapping("/post/list")
    public String postList(Model model, @RequestParam(value="page", defaultValue="0") int page) {
        Page<Post> paging = this.postService.getPostList(page);
        model.addAttribute("paging", paging);
        return "post_list";
    }

    @GetMapping(value = "/post/detail/{id}")
    public String postDetail(Model model, @PathVariable("id") Integer id, CommentCreateForm commentCreateForm) {
        Post post = this.postService.getPost(id);
        model.addAttribute("post", post);
        return "post_detail";
    }

    @PostMapping("/modify/{id}")
    public Post postModify(PostUpdateForm postUpdateForm, @PathVariable("id") Integer id) {
        Post post = this.postService.getPost(id);
        this.postService.modifyPost(post, postUpdateForm.getSubject(), postUpdateForm.getContent());
        return this.postService.getPost(id);
    }

    @DeleteMapping("/delete/{id}")
    public String postDelete(@PathVariable("id") Integer id) {
        Post post = this.postService.getPost(id);
        this.postService.delete(post);
        return "Delete success.";
    }

}
