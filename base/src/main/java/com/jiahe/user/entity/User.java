package com.jiahe.user.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "USER")
public class User {

    @Id
    private String id;

    /**
     *
     * USER ID
     */
    private String uid;

    /**
     * 用户名
     */
    @Column(name = "USERNAME", length = 20)
    private String username;

    /**
     * 电话
     */
    @Column(name = "PHONE", length = 20)
    private String phone;

    /**
     * 密码
     */
    @Column(name = "PASSWORD", length = 100)
    private String password;

    /**
     * 邮箱
     */
    @Column(name = "EMAIL", length = 100)
    private String email;

    /**
     * 头像
     */
    @Column(name = "AVATAR", length = 50)
    private String avatar;

    /**
     * 账号状态
     */
    @Column(name = "STATE", length = 10)
    private String state;

    /**
     * 账号类型
     */
    @Column(name = "TYPE", length = 10)
    private String type;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;

}
