package com.Forum.Forum.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {
    @Size(min = 3, max = 25)
    @NotEmpty(message = "This is a required field.")
    private String username;

    @NotEmpty(message = "This is a required field.")
    private String password;

    @NotEmpty(message = "This is a required field.")
    private String passwordConfirm;

    @NotEmpty(message = "This is a required field.")
    @Email
    private String email;
}
