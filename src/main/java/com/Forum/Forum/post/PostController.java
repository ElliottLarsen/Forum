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

import java.util.List;

@RequiredArgsConstructor
@Controller
public class PostController {
    private final PostService postService;

    @GetMapping("/post/create")
    public String postCreate(PostCreateForm postCreateForm) {
        return "post_form";
    }

    @PostMapping("/post/create")
    public String postCreate(@Valid PostCreateForm postCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "post_form";
        }
        this.postService.createPost(postCreateForm.getSubject(), postCreateForm.getContent());
        return "redirect:/post/list";
    }

    @GetMapping("/post/list")
    public String postList(Model model) {
        List<Post> postList = this.postService.getPostList();
        model.addAttribute("postList", postList);
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
