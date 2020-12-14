package com.galaxy.galaxyblog.config.excep;

import com.galaxy.galaxyblog.common.HttpResultResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

/**
 * TODO 请说明此类的作用
 *
 * @author wangkq
 * @date 2020/12/11
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    //自定义
    @ExceptionHandler({MyException.class})
    public HttpResultResp MyException(MyException ex) {
        return httpResult(ex.getCode(), ex);
    }

    private <T extends Throwable> HttpResultResp httpResult(Integer code, T ex) {
        ex.printStackTrace();
        return HttpResultResp.error(code, ex.getMessage());
    }

}
