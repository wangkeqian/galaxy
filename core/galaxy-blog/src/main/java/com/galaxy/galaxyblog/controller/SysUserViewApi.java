package com.galaxy.galaxyblog.controller;

import com.galaxy.galaxyblog.common.HttpResultResp;
import com.galaxy.galaxyblog.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * TODO 请说明此类的作用
 *
 * @author wangkq
 * @date 2020/12/14
 */
@RestController
@RequestMapping("/user")
public class SysUserViewApi {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("getSysUser/{id}")
    public HttpResultResp getSysUserById(@PathVariable String id){
        return HttpResultResp.ok(sysUserService.getSysUserById(id));
    }
    @PostMapping("loginStatus")
    public HttpResultResp loginStatus( String token){
        return HttpResultResp.ok(sysUserService.loginStatus(token));
    }
    @GetMapping("isFollowing/{id}")
    public HttpResultResp isFollowing(@PathVariable String id){
        return HttpResultResp.ok(sysUserService.isFollowing(id));
    }
}
