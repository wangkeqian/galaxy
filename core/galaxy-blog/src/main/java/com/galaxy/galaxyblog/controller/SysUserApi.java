package com.galaxy.galaxyblog.controller;

import com.galaxy.galaxyblog.common.HttpResultResp;
import com.galaxy.galaxyblog.common.utils.RedisUtil;
import com.galaxy.galaxyblog.model.SysUser;
import com.galaxy.galaxyblog.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * 用户中心
 *
 * @author wangkq
 * @date 2020/12/6
 */
@RestController
@RequestMapping("/user")
public class SysUserApi {

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/register")
    public HttpResultResp register(@RequestBody SysUser user){
        return HttpResultResp.ok(sysUserService.register(user));
    }
    @PostMapping("/login")
    public HttpResultResp login(String username, String password, HttpServletResponse response){
        SysUser sysUser = sysUserService.loginAndVerify(username, password, response);
        if (sysUser != null){
            return HttpResultResp.ok(sysUser);
        }else {
            return HttpResultResp.error("登录失败，账号或密码错误");
        }
    }
    @PostMapping("loginStatus")
    public HttpResultResp loginStatus( String token){
        return HttpResultResp.ok(sysUserService.loginStatus(token));
    }

}
