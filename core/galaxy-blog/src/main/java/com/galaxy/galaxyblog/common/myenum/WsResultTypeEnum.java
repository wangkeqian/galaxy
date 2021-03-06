package com.galaxy.galaxyblog.common.myenum;

/**
 * 推送类型
 */
public enum WsResultTypeEnum {
    MY_REPLY(1,"我的回复"),
    MY_FOCUS(2,"我的关注");
    WsResultTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private int code;
    private String desc;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
