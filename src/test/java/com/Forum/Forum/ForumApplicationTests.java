package com.Forum.Forum;

import java.time.LocalDateTime;

import com.Forum.Forum.post.Post;
import com.Forum.Forum.post.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class ForumApplicationTests {

	@Autowired
	private PostRepository postRepository;

	@Test
	void testJpa() {
		Post p1 = new Post();
		p1.setSubject("What is Forum?");
		p1.setContent("Could you tell me more about Forum?");
		p1.setCreateDate(LocalDateTime.now());
		this.postRepository.save(p1);

		Post p2 = new Post();
		p2.setSubject("Registration Question");
		p2.setContent("I would like to register.");
		p2.setCreateDate(LocalDateTime.now());
		this.postRepository.save(p2);
	}
}
