package com.galaxy.galaxyblog.model;

import lombok.Data;

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

    private String address;
    /**
     * 是否可用
     */
    private String enable;

    private String remark;

}
