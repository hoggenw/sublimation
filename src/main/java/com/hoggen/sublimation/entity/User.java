package com.hoggen.sublimation.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class User {

    // ID
    private String userId;
    //头像
    private String avatar;

    // 名称
    private String userName;
    // 密码
    private String password;
    // mobile
    private String mobile;
//    // 真实姓名
//    private String realName;
    //
    private String randomString;
    // 用户状态(0正常，1冻结)
    private Integer status;
    // 创建时间
    private Date createTime;
    // 登录时间
    private Date lastLoginTime;
    // 更新时间S
    private Date updateTime;
//
//    // 备注
//    private String remark;

    private Integer roleType;
   //7聊号
    private String codeName;

}
