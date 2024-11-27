package com.jiahe.user.controller;

import com.jiahe.response.BaseResponse;
import com.jiahe.user.entity.User;
import com.jiahe.user.service.UserService;
import io.lettuce.core.dynamic.annotation.Param;
import lombok.RequiredArgsConstructor;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final StringEncryptor stringEncryptor;

    @PostMapping("/register")
    public BaseResponse register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public BaseResponse login(@RequestBody User user) {
        return userService.login(user);
    }

    @GetMapping("/test")
    public BaseResponse test(@RequestParam String username) {
        System.out.println(stringEncryptor.encrypt(username));
        return BaseResponse.success();
    }

}
