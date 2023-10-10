package com.Forum.Forum.comment;

import jakarta.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentCreateForm {
    @NotEmpty(message = "Comment is a required field.")
    private String content;
}
