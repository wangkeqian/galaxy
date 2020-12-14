package com.galaxy.galaxyblog.config.excep;

import com.alibaba.druid.sql.visitor.functions.Right;
import com.galaxy.galaxyblog.common.myenum.ErrorExceptionEnum;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 自定义异常类
 *
 * @author wangkq
 * @date 2020/12/11
 */
public class MyException extends RuntimeException{
    private int code ;

    public MyException(ErrorExceptionEnum errorExceptionEnum){
        super(errorExceptionEnum.getDesc());
        this.code = errorExceptionEnum.getCode();
    }

    public int getCode() {
        return code;
    }
}
