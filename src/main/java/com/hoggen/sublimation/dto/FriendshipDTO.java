package com.hoggen.sublimation.dto;

import com.hoggen.sublimation.proto.MessageModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Data
public class FriendshipDTO {

    // ID
    private String id;

    private Integer status;

    // ID
    // 名称

    private String userId;

    // ID
    private String friendId;

    //别名
    private String firendCategoryName;

    //别名
    private String userCategoryName;

    //别名

    private String friendRemark;

    //别名
    private String userRemark;


//    //是否已经删除
//    private Integer delete;

    private UserModelDTO user;

    private UserModelDTO friend;

}
