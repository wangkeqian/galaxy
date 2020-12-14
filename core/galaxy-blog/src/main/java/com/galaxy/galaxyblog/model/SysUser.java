package com.galaxy.galaxyblog.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

/**
 * 用户表
 *
 * @author wangkq
 * @date 2020/12/6
 */
@Data
public class SysUser extends DataEntity{
    /**
     * 真实姓名
     */
    private String name;
    /**
     * 用户名
     */
    private String username;

    private String password;

    private String email;

    private String headPhoto;

    private String phone;

    private Date birthday;
    /**
     * 工作岗位/职位
     */
    private String position;

    private String address;
    /**
     * 是否可用
     */
    private String enable;

    /**
     * 个人简介
     */
    private String intro;

}
