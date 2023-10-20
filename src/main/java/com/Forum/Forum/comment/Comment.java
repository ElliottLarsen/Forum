package com.Forum.Forum.comment;

import java.time.LocalDateTime;

import com.Forum.Forum.post.Post;
import com.Forum.Forum.user.SiteUser;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    @JsonBackReference
    @ManyToOne
    private Post post;

    @ManyToOne
    private SiteUser author;
}
