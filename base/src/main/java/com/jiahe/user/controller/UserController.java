package com.jiahe.user.controller;

import com.jiahe.response.BaseResponse;
import com.jiahe.user.entity.User;
import com.jiahe.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public BaseResponse register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public BaseResponse login(@RequestBody User user) {
        return userService.login(user);
    }

}
