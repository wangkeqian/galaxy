package com.galaxy.galaxyblog.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * TODO 请说明此类的作用
 *
 * @author wangkq
 * @date 2020/10/24
 */
public class HttpResultResp {
    private Integer status;
    private String msg;
    private Object obj;

    public static HttpResultResp build() {
        return new HttpResultResp();
    }

    public static HttpResultResp ok() {
        return new HttpResultResp(200, "ok", null);
    }

    public static HttpResultResp ok(String msg) {
        return new HttpResultResp(200, msg, null);
    }

    public static HttpResultResp ok(Object obj) {
        return new HttpResultResp(200, "ok", obj);
    }

    public static HttpResultResp ok(String msg, Object obj) {
        return new HttpResultResp(200, msg, obj);
    }

    public static HttpResultResp error(String msg) {
        return new HttpResultResp(500, msg, null);
    }

    public static HttpResultResp error(int code, String msg) {
        return new HttpResultResp(code, msg, null);
    }

    public static HttpResultResp error(String msg, Object obj) {
        return new HttpResultResp(500, msg, obj);
    }

    private HttpResultResp() {
    }

    private HttpResultResp(Integer status, String msg, Object obj) {
        this.status = status;
        this.msg = msg;
        this.obj = obj;
    }

    public Integer getStatus() {
        return status;
    }

    public HttpResultResp setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public HttpResultResp setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getObj() {
        return obj;
    }

    public HttpResultResp setObj(Object obj) {
        this.obj = obj;
        return this;
    }
}
