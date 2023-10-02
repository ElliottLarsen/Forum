package com.Forum.Forum.post;

import com.Forum.Forum.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PostRepository extends JpaRepository<Post, Integer>{
}
