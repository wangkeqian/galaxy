package com.galaxy.galaxyblog.common.myenum;

//推送样式
public enum WsResultStyleEnum {
    POP_UP(1,"弹窗消息"),
    BANNER(2,"横幅消息/红点提示"),
    QUIET(3,"静默消息");
    private int code;
    private String desc;

    WsResultStyleEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

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
