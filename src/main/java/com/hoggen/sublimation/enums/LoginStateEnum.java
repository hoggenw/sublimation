package com.hoggen.sublimation.enums;

public enum LoginStateEnum {
    USERNONE(11000, "用户名或者密码错误"),
    SUCCESS(0, "操作成功"),
    OVERTIME(11001, "登录已过期"),
    NOTLOGIN(11002, "请登录"),
    NOPERMISSION(11003, "没有权限"),
    EMPTY(11004, "用户不存在或者被冻结"),
    FREEZE(11005, "用户被冻结"),
    CODEERROR(11006, "验证码错误")
    ,QUITERROR(11007, "退出登录失败");

    private int state;

    private String stateInfo;

    private LoginStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static LoginStateEnum stateOf(int index) {
        for (LoginStateEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }
}
