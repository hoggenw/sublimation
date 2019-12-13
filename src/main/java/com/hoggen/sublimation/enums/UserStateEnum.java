package com.hoggen.sublimation.enums;

public enum UserStateEnum {
    OFFLINE(10000, "非法用户"),
    SUCCESS(0, "操作成功"),
    PASS(10002, "通过认证"),
    INNER_ERROR(10001, "操作失败"),
    EMPTY(10003, "用户为空"),
    NAMEOFFLINE(10004, "用户名不合法"),
    PASSOFFLINE(10005, "密码不合法"),
    ALREADYPHONE(10006, "该电话已经被使用"),
    ALREADYCODENAME(10006, "该7聊号已经被使用"),
    PHONEEMPTY(10007, "电话号码为空"),
    REALNAMEEMPTY(10008, "真是姓名为空"),
    ROLEIILLEGAl(10009, "用户角色非法"),
    OPEILLEGAl(10010, "不能操作自己"),
    INFOILLEGAl(10011, "参数错误"),
    NAMESensitive(10012, "名称含有敏感词汇"),
    CODENAMESensitive(10013, "7聊号含有敏感词汇"),
    APPLYFRIENDFAILED(10014, "申请朋友操作失败"),
    APPLYALREADY(10015, "你已经申请，请耐心等待对方同意"),
    AGREEALREADY(10016, "该好友已经添加"),
    FRIENDSHIPEMPTY(10017, "该好友关系不存在,或者被删除"),
    FRIENDSHIPILLEGAL(10018, "用户角色非法"),
    FRIENDSHIPBLACKALLREADY(10019, "该用户已经在您的黑名单中,或者你已经在对方黑名单中");


    private int state;

    private String stateInfo;

    private UserStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static UserStateEnum stateOf(int index) {
        for (UserStateEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }
}
