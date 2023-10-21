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
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

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

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/post/modify/{id}")
    public String postModify(PostCreateForm postForm, @PathVariable("id") Integer id, Principal principal) {
        Post post = this.postService.getPost(id);
        if(!post.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You are not authorized to edit this.");
        }
        postForm.setSubject(post.getSubject());
        postForm.setContent(post.getContent());
        return "post_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/post/modify/{id}")
    public String postModify(@Valid PostCreateForm postForm, BindingResult bindingResult,
                                 Principal principal, @PathVariable("id") Integer id) {
        System.out.println("HELLO");
        if (bindingResult.hasErrors()) {
            return "post_form";
        }
        Post post = this.postService.getPost(id);
        if (!post.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You are not authorized to edit this.");
        }
        this.postService.modify(post, postForm.getSubject(), postForm.getContent());
        return String.format("redirect:/post/detail/%s", id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/post/delete/{id}")
    public String postDelete(Principal principal, @PathVariable("id") Integer id) {
        Post post = this.postService.getPost(id);
        if (!post.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You are not authorized to delete this.");
        }
        this.postService.delete(post);
        return "redirect:/post/list";
    }

}
