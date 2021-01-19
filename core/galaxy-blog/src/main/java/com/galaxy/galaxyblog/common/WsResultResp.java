package com.galaxy.galaxyblog.common;

import com.galaxy.galaxyblog.common.myenum.WsResultStyleEnum;
import com.galaxy.galaxyblog.common.myenum.WsResultTypeEnum;

/**
 * websocket返回实体类
 *
 * @author wangkq
 * @date 2020/12/17
 */
public class WsResultResp {
    private int status;
    private String title;
    private String msg;
    private Object obj;
    private int type;

    public static WsResultResp build() {
        return new WsResultResp();
    }
    public static WsResultResp POP_UP(String msg, WsResultTypeEnum wt) {
        return new WsResultResp(WsResultStyleEnum.POP_UP, wt,  "你有新的动态啦!", msg, null);
    }
    public static WsResultResp POP_UP(String title, String msg, WsResultTypeEnum wt) {
        return new WsResultResp(WsResultStyleEnum.POP_UP, wt, title, msg, null);
    }
    public static WsResultResp POP_UP(String title, String msg,Object obj, WsResultTypeEnum wt) {
        return new WsResultResp(WsResultStyleEnum.POP_UP, wt, title, msg, obj);
    }
    public static WsResultResp BANNER(String msg, WsResultTypeEnum wt) {
        return new WsResultResp(WsResultStyleEnum.BANNER, wt, msg, null);
    }
    public static WsResultResp BANNER(String msg,Object obj, WsResultTypeEnum wt) {
        return new WsResultResp(WsResultStyleEnum.BANNER, wt, msg, obj);
    }
    public static WsResultResp QUIET(String msg) {
        return new WsResultResp(WsResultStyleEnum.QUIET, msg, null);
    }
    public static WsResultResp QUIET(String msg,Object obj) {
        return new WsResultResp(WsResultStyleEnum.QUIET, msg, obj);
    }


    private WsResultResp() {
    }

    public WsResultResp(WsResultStyleEnum status, String msg, Object obj) {
        this.status = status.getCode();
        this.title = title;
        this.msg = msg;
        this.obj = obj;
    }

    private WsResultResp(WsResultStyleEnum status, WsResultTypeEnum type, String msg, Object obj) {
        this.status = status.getCode();
        this.msg = msg;
        this.obj = obj;
        this.type = type.getCode();
    }

    public WsResultResp(WsResultStyleEnum status, WsResultTypeEnum type, String title, String msg, Object obj) {
        this.status = status.getCode();
        this.title = title;
        this.msg = msg;
        this.obj = obj;
        this.type = type.getCode();
    }

    public int getStatus() {
        return status;
    }

    public int setStatus(WsResultStyleEnum status) {
        this.status = status.getCode();
        return this.status;
    }

    public String getMsg() {
        return msg;
    }

    public WsResultResp setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getObj() {
        return obj;
    }

    public WsResultResp setObj(Object obj) {
        this.obj = obj;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
