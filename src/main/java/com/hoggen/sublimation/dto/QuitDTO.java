package com.hoggen.sublimation.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Data
@Accessors(chain = true)
@ApiModel("退出登录模型")
public class QuitDTO {
    // 密码
    @ApiModelProperty(value = "userId")
    @NotBlank
    private String userId;
//    // mobile
//    @ApiModelProperty(value = "token")
//    @NotBlank
//    private String token;

}
