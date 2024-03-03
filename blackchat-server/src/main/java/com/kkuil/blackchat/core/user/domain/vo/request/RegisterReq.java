package com.kkuil.blackchat.core.user.domain.vo.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * @Author Kkuil
 * @Date 2024/3/3 20:33
 * @Description 登录请求实体VO
 */
@Data
public class LoginReq {
    /**
     * 用户名
     */
    @NotEmpty(message = "用户名不能为空")
    private String name;

    /**
     * 密码
     */
    @NotEmpty(message = "密码不能为空")
    private String password;
}
