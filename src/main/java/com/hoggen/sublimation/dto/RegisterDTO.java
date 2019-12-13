package com.hoggen.sublimation.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Accessors(chain = true)
@ApiModel("注册输入模型")
public class RegisterDTO   implements Serializable {
    // 密码
    @ApiModelProperty(value = "密码")
    @NotBlank
    private String password;
    // mobile
    @ApiModelProperty(value = "账号")
    @NotBlank
    private String phone;

    @ApiModelProperty(value = "昵称")
    @NotBlank
    private String name;

    @ApiModelProperty(value = "验证码")
    @NotBlank
    private String code;

    @ApiModelProperty(value = "7聊号")
    @NotBlank
    private String codeName;




}
