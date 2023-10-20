package com.Forum.Forum.comment;

import com.Forum.Forum.post.Post;
import com.Forum.Forum.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public Comment create(Post post, String content, SiteUser author) {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setCreateDate(LocalDateTime.now());
        comment.setPost(post);
        comment.setAuthor(author);
        this.commentRepository.save(comment);
        return comment;
    }
}
