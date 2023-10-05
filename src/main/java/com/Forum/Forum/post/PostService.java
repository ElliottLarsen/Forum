package com.Forum.Forum.post;

import java.util.List;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.Optional;
import com.Forum.Forum.DataNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    public Page<Post> getPostList(int page) {

        Pageable pageable = PageRequest.of(page, 10);
        return this.postRepository.findAll(pageable);
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
