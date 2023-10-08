package com.Forum.Forum.post;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCreateForm {
    @NotEmpty(message = "Subject is a required field.")
    @Size(max = 200)
    private String subject;

    @NotEmpty(message = "Content is a required field.")
    private String content;
}
