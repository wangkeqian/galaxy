package com.galaxy.galaxyblog.service;

import com.galaxy.galaxyblog.common.myenum.ErrorExceptionEnum;
import com.galaxy.galaxyblog.common.utils.BeanCommonsUtils;
import com.galaxy.galaxyblog.common.utils.RedisUtil;
import com.galaxy.galaxyblog.config.excep.MyException;
import com.galaxy.galaxyblog.config.login.LoginIntercept;
import com.galaxy.galaxyblog.mapper.SysUserMapper;
import com.galaxy.galaxyblog.model.SysUser;
import com.galaxy.galaxyblog.model.vo.SysUserVo;
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

    public SysUserVo loginAndVerify(String username, String password) {
        SysUser sysUser = sysUserMapper.verifySysUser(username, password);
        String token = "";
        if (null != sysUser){
            Long milliSecond = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
            Map<String, Object> userMap = BeanCommonsUtils.objectToMap(sysUser);
            userMap.put("token",milliSecond);
            redisUtil.hmset("galaxy:user:"+sysUser.getId(),userMap,6000);
            token = "galaxy:user:"+sysUser.getId()+"_"+milliSecond;
        }
        return new SysUserVo(sysUser,token);
    }

    public SysUser loginStatus(String token) {
        SysUser sysUser = null;
        String[] info = token.split("_");
        Map<Object, Object> user = redisUtil.hmget(info[0]);
        if (null != user && String.valueOf(user.get("token")).equals(info[1])){
            sysUser = (SysUser) BeanCommonsUtils.map2Object(user, SysUser.class);
        }
        return sysUser;
    }

    public SysUser getSysUserById(String id) {
        SysUser sysUser = sysUserMapper.selectById(id);
        if (null == sysUser) throw new MyException(ErrorExceptionEnum.USER_NOT_FOUND);
        return sysUser;
    }

    /**
     * 对方新增粉丝，己方多了关注者
     */
    public String addFollower(String id) {
        Map loginUserInfo = LoginIntercept.getLoginUserInfo();
        Long milliSecond = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        redisUtil.zAdd("following:"+loginUserInfo.get("id"),id,milliSecond);//我的关注列表新增
        redisUtil.zAdd("followers:"+id,loginUserInfo.get("id"),milliSecond);//对方的粉丝列表新增
        return "关注成功";
    }

    public Boolean isFollowing(String id) {
        Map loginUserInfo = LoginIntercept.getLoginUserInfo();
        Double score = redisUtil.score("following:" + loginUserInfo.get("id"), id);
        return score != null;
    }

    public String cancelFollowing(String id) {
        Map loginUserInfo = LoginIntercept.getLoginUserInfo();
        redisUtil.zRem("following:" + loginUserInfo.get("id"),id);
        redisUtil.zRem("followers:"+id,String.valueOf(loginUserInfo.get("id")));
        return "已取消关注";
    }
}
