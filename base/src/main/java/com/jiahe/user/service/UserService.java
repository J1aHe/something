package com.jiahe.user.service;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.IdUtil;
import cn.hutool.jwt.JWT;
import com.jiahe.api.BaseAPI;
import com.jiahe.exception.user.UserException;
import com.jiahe.response.BaseResponse;
import com.jiahe.user.dao.UserRepository;
import com.jiahe.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class UserService {

    private final RedisTemplate<String, String> redisTemplate;
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
        Map<String, String> payload = new HashMap<>();
        payload.put("iss", "something");
        payload.put("exp", String.valueOf(LocalDateTime.now().plusDays(7)));
        String token = JWT.create()
                .addPayloads(payload)
                .setKey(BaseAPI.J1aHe.getBytes(StandardCharsets.UTF_8))
                .sign();

        // redis存储
        redisTemplate.opsForValue().set("token", token, 3, TimeUnit.DAYS);

        return BaseResponse.success().message("登录成功").data(token);
    }
}
