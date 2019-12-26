package com.hoggen.sublimation.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpdateAvatarDTO {

    // mobile
    @ApiModelProperty(value = "头像")
    @NotBlank
    private String avatar;
}
