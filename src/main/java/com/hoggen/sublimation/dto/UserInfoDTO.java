package com.hoggen.sublimation.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;


@Data
@Accessors(chain = true)
@ApiModel("获取用户信息")

public class UserInfoDTO  implements Serializable {
    // 密码
    @ApiModelProperty(value = "用户id")
    @NotBlank
    private String userId;
    // mobile
    @ApiModelProperty(value = "朋友关系证明")
    private String applyId;
}
