package com.hoggen.sublimation.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.util.Date;


import java.util.Date;

@Data
@Accessors(chain = true)
@ApiModel("好友申请模型")
public class FriendshipApply {
    // ID
    private String id;

    // ID
    // 名称
    @ApiModelProperty(value = "申请人id")
    @NotBlank
    private String userId;

    // ID
    @ApiModelProperty(value = "申请对象id")
    @NotBlank
    private String friendId;

    @ApiModelProperty(value = "请求状态")
    private Integer status;


//    private String fuId;
//
//    private String ufId;

    // 创建时间
    private Date createTime;

    //是否已经删除
    @ApiModelProperty(value = "是否删除")
    private Integer deleteStatus;

}
