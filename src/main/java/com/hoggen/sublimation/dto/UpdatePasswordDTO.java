package com.hoggen.sublimation.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Data
@Accessors(chain = true)
@ApiModel("修改密码模型")
public class UpdatePasswordDTO {
    // 密码
    @ApiModelProperty(value = "新密码")
    @NotBlank
    private String newPassword;
    // mobile
    @ApiModelProperty(value = "旧")
    @NotBlank
    private String oldPassword;
}
