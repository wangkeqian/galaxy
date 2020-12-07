package com.galaxy.galaxyblog.service;

import com.galaxy.galaxyblog.common.utils.BeanCommonsUtils;
import com.galaxy.galaxyblog.common.utils.RedisUtil;
import com.galaxy.galaxyblog.mapper.SysUserMapper;
import com.galaxy.galaxyblog.model.SysUser;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;

/**
 * TODO 请说明此类的作用
 *
 * @author wangkq
 * @date 2020/12/6
 */
@Service
public class SysUserService {
    @Resource
    private SysUserMapper sysUserMapper;
    @Autowired
    private RedisUtil redisUtil;

    public String register(SysUser user) {
        sysUserMapper.insert(user);
        return "注册成功";
    }

    public SysUser loginAndVerify(String username, String password, HttpServletResponse response) {
        SysUser sysUser = sysUserMapper.verifySysUser(username, password);
        if (null != sysUser){
            Long milliSecond = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
            Map<String, Object> userMap = BeanCommonsUtils.objectToMap(sysUser);
            userMap.put("token",milliSecond);
            redisUtil.hmset("galaxy:user:"+sysUser.getId(),userMap,600);
            response.addCookie(new Cookie("userToken","galaxy:user:"+sysUser.getId()+"_"+milliSecond));
        }
        return sysUser;
    }

    public SysUser loginStatus(String token) {
        SysUser sysUser = null;
        String[] info = token.split("_");
        Map<Object, Object> user = redisUtil.hmget(info[0]);
        if (null != user && String.valueOf(user.get("token")).equals(info[1])){
            sysUser = (SysUser) BeanCommonsUtils.mapToObject(user, SysUser.class);
        }
        return sysUser;
    }
}
