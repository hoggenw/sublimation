package com.hoggen.sublimation.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Data
@Accessors(chain = true)
@ApiModel("修改备注名")
public class UpdateUserCategoryDTO {
    // ID
    // mobile
    @ApiModelProperty(value = "关系表id")
    @NotBlank
    private String id;

    // ID
    // 名称
    @ApiModelProperty(value = "备注名称")
    private String name;


    @ApiModelProperty(value = "申请备注")
    private String remark;
}
