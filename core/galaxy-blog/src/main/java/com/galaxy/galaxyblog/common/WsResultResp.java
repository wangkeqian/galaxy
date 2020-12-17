package com.galaxy.galaxyblog.common;

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

    public static WsResultResp build() {
        return new WsResultResp();
    }
    public static WsResultResp POP_UP(String msg) {
        return new WsResultResp(WsResultTypeEnum.POP_UP, "你有新的动态啦!", msg, null);
    }
    public static WsResultResp POP_UP(String title, String msg) {
        return new WsResultResp(WsResultTypeEnum.POP_UP, title, msg, null);
    }
    public static WsResultResp POP_UP(String title, String msg,Object obj) {
        return new WsResultResp(WsResultTypeEnum.POP_UP, title, msg, obj);
    }
    public static WsResultResp BANNER(String msg) {
        return new WsResultResp(WsResultTypeEnum.BANNER, msg, null);
    }
    public static WsResultResp BANNER(String msg,Object obj) {
        return new WsResultResp(WsResultTypeEnum.BANNER, msg, obj);
    }
    public static WsResultResp QUIET(String msg) {
        return new WsResultResp(WsResultTypeEnum.QUIET, msg, null);
    }
    public static WsResultResp QUIET(String msg,Object obj) {
        return new WsResultResp(WsResultTypeEnum.QUIET, msg, obj);
    }


    private WsResultResp() {
    }

    private WsResultResp(WsResultTypeEnum status, String msg, Object obj) {
        this.status = status.getCode();
        this.msg = msg;
        this.obj = obj;
    }

    public WsResultResp(WsResultTypeEnum status, String title, String msg, Object obj) {
        this.status = status.getCode();
        this.title = title;
        this.msg = msg;
        this.obj = obj;
    }

    public int getStatus() {
        return status;
    }

    public int setStatus(WsResultTypeEnum status) {
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
}
