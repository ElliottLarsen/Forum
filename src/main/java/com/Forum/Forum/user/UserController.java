package com.Forum.Forum.user;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    // TODO: Return the user object.
    @PostMapping(value = "/user/signup/")
    @ResponseBody
    public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ERROR";
        }

        if (!userCreateForm.getPassword().equals(userCreateForm.getPasswordConfirm())) {
            bindingResult.rejectValue("password", "passwordConfirm", "Passwords do not match.");
            return "Error";
        }
        userService.create(userCreateForm.getUsername(), userCreateForm.getEmail(), userCreateForm.getPassword());
        return "Success";
    }
}
