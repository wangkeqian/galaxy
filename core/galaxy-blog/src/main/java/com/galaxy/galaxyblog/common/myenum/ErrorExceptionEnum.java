package com.galaxy.galaxyblog.common.myenum;

import lombok.Data;

/**
 * 自定义异常类枚举
 *
 * @author wangkq
 * @date 2020/12/11
 */
public enum ErrorExceptionEnum {

    USERNAME_PASSWORD_NOT_MATCH(9001,"账户或密码错误，请重新输入"),
    NOT_LOGIN_SYS(9002,"请登录系统"),
    LOGIN_TIMEOUT(9003,"登录超时,请重新登录"),
    USER_NOT_FOUND(9004,"用户不存在或以注销"),
    WEIGHT_FACTOR_ADD_ERROR(9005,"排序加权因子相加不等于 100！");

    private int code;
    private String desc;
    ErrorExceptionEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
