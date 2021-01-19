package com.galaxy.galaxyblog.config.login;

import com.galaxy.galaxyblog.common.utils.BeanCommonsUtils;
import com.galaxy.galaxyblog.common.utils.RedisUtil;
import com.galaxy.galaxyblog.config.excep.MyException;
import com.galaxy.galaxyblog.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static com.galaxy.galaxyblog.common.myenum.ErrorExceptionEnum.*;

/**
 * TODO 请说明此类的作用
 *
 * @author wangkq
 * @date 2020/12/11
 */
@Component
public class LoginIntercept implements HandlerInterceptor {

    @Resource
    RedisUtil redisUtil;
    private final static ThreadLocal<Map> userInfo = ThreadLocal.withInitial(() -> null);
    Map<Object, Object> user = null;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (token == null || token == "") throw new MyException(NOT_LOGIN_SYS);
        String[] info = token.split("_");
        user = redisUtil.hmget(info[0]);
        if (user == null || user.size() == 0) throw new MyException(LOGIN_TIMEOUT);
        if (!String.valueOf(user.get("token")).equals(info[1])) throw new MyException(USERNAME_PASSWORD_NOT_MATCH);
        userInfo.set(user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    public static Map getLoginUserInfo(){
        return userInfo.get();
    }
    public static String getLoginUserId(){
        return String.valueOf(userInfo.get().get("id"));
    }
}
