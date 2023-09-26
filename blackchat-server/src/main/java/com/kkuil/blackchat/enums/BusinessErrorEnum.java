package com.kkuil.blackchat.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author Kkuil
 * @Date 2023/9/26
 * @Description 业务校验异常码
 */
@AllArgsConstructor
@Getter
public enum BusinessErrorEnum implements ErrorEnum {
    //==================================common==================================
    BUSINESS_ERROR(1001, "{0}"),
    //==================================user==================================
    //==================================chat==================================
    SYSTEM_ERROR(1001, "系统出小差了，请稍后再试哦~~"),
    ;
    private final Integer code;
    private final String msg;

    @Override
    public Integer getErrorCode() {
        return code;
    }

    @Override
    public String getErrorMsg() {
        return msg;
    }
}
