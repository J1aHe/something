package com.jiahe.user.service;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.IdUtil;
import cn.hutool.jwt.JWT;
import com.jiahe.exception.user.UserException;
import com.jiahe.response.BaseResponse;
import com.jiahe.user.dao.UserRepository;
import com.jiahe.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public BaseResponse register(User user) {

        Assert.notEmpty(user.getUsername());
        Assert.notEmpty(user.getPassword());

        // 用户名重复 校验
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UserException("用户名已存在");
        }

        // 设置基础信息
        user.setId(IdUtil.getSnowflakeNextIdStr());
        user.setCreateDate(LocalDateTime.now());
        userRepository.save(user);

        return BaseResponse.success().message("注册成功");
    }

    public BaseResponse login(User user) {

        Assert.notEmpty(user.getUsername());
        Assert.notEmpty(user.getPassword());

        // 验证账号密码
        User byAccount = userRepository.findByAccountAndPassword(user.getUsername(), user.getPassword());
        if (Objects.isNull(byAccount)) {
            throw new UserException("用户名或密码错误");
        }

        // 生成token


        return null;
    }
}
