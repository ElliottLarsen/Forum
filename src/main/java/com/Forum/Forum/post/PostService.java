package com.Forum.Forum.post;

import java.util.List;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.Optional;
import com.Forum.Forum.DataNotFoundException;
import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    public void createPost(String subject, String content) {
        Post p = new Post();
        p.setSubject(subject);
        p.setContent(content);
        p.setCreateDate(LocalDateTime.now());
        this.postRepository.save(p);
    }

    public List<Post> getPostList() {
        //Pageable pageable = PageRequest.of(page, 10);
        return this.postRepository.findAll();
    }

    public Post getPost(Integer id) {
        Optional<Post> post = this.postRepository.findById(id);
        if (post.isPresent()) {
            return post.get();
        } else {
            throw new DataNotFoundException("Post not found.");
        }
    }

    public void modifyPost(Post post, String subject, String content) {
        post.setSubject(subject);
        post.setContent(content);
        this.postRepository.save(post);
    }

    public void delete(Post post) {
        this.postRepository.delete(post);
    }
}
