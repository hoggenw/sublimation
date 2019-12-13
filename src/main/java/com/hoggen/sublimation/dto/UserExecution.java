package com.hoggen.sublimation.dto;


import com.hoggen.sublimation.entity.User;
import com.hoggen.sublimation.enums.UserStateEnum;

public class UserExecution {

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // 结果状态
    private int state;

    // 状态标识
    private String stateInfo;

    // 用户数量
    private int count;

    // 操作的User
    private User user;


    public UserExecution() {
    }

    // 失败的构造器
    public UserExecution(UserStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    // 成功的构造器
    public UserExecution(UserStateEnum stateEnum, User user) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.user = user;
    }


}
