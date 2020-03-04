package com.hoggen.sublimation.dto;

import com.hoggen.sublimation.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
@ApiModel("返回用户模型")
public class ReturnUserDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    // ID
    @ApiModelProperty(value = "userId")
    @NotBlank
    private String userID;
    //头像
    @ApiModelProperty(value = "头像连接")
    private String avatar;

    // 名称
    @ApiModelProperty(value = "昵称")
    @NotBlank
    private String name;

    // mobile
    @ApiModelProperty(value = "phone")
    @NotBlank
    private String phone;

    // 用户状态(1正常，0冻结)
    @ApiModelProperty(value = "用户状态(1正常，0冻结)")
    @NotBlank
    private Integer status;

    @ApiModelProperty(value = "roleType")
    private Integer roleType;

    @ApiModelProperty(value = "7聊号")
    //
    private String codeName;


    public ReturnUserDTO(User user,Integer type) {
        this.userID = user.getUserId();
        this.avatar = user.getAvatar();
        this.name = user.getUserName();
        if (type == 1){
            this.phone = user.getMobile();
        }
        this.status = user.getStatus();
        this.roleType = user.getRoleType();
        this.codeName = user.getCodeName();

    }



}
