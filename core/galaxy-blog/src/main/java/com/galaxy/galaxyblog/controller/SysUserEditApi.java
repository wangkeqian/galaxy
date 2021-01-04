package com.galaxy.galaxyblog.controller;

import com.galaxy.galaxyblog.common.HttpResultResp;
import com.galaxy.galaxyblog.common.myenum.ErrorExceptionEnum;
import com.galaxy.galaxyblog.common.utils.RedisUtil;
import com.galaxy.galaxyblog.config.excep.MyException;
import com.galaxy.galaxyblog.model.SysUser;
import com.galaxy.galaxyblog.model.vo.SysUserVo;
import com.galaxy.galaxyblog.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * 用户中心
 *
 * @author wangkq
 * @date 2020/12/6
 */
@RestController
@RequestMapping("/user")
public class SysUserEditApi {

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/register")
    public HttpResultResp register(@RequestBody SysUser user){
        return HttpResultResp.ok(sysUserService.register(user));
    }
    @GetMapping("/login")
    public HttpResultResp login(String username, String password) throws Exception {
        SysUserVo sysUser = sysUserService.loginAndVerify(username, password);
        if (sysUser.getSysUser() == null) throw new MyException(ErrorExceptionEnum.USERNAME_PASSWORD_NOT_MATCH);
        return HttpResultResp.ok(sysUser);
    }
    @GetMapping("/addFollower/{id}")
    public HttpResultResp addFollower(@PathVariable String id){
        return HttpResultResp.ok(sysUserService.addFollower(id));
    }
    @GetMapping("/cancelFollowing/{id}")
    public HttpResultResp cancelFollowing(@PathVariable String id){
        return HttpResultResp.ok(sysUserService.cancelFollowing(id));
    }
}
