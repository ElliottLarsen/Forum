package com.Forum.Forum.post;

import java.util.List;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.Optional;
import com.Forum.Forum.DataNotFoundException;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    public List<Post> getPostList() {
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
}
