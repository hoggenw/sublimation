package com.hoggen.sublimation.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Accessors(chain = true)
public class ImageInformation implements Serializable {

    @ApiModelProperty(value = "图片id")
    private String id;

    @ApiModelProperty(value = "图片名")
    private String fileName;

    @ApiModelProperty(value = "扩展名")
    private String prefix;

    @ApiModelProperty(value = "图片路径")
    private String filePath;

    @ApiModelProperty(value = "图片曾用名")
    private String formerName;

    @ApiModelProperty(value = "创建时间''")
    private Date createDate;

    @ApiModelProperty(value = "最后修改时间")
    private Date lastModifiedDate;

    @ApiModelProperty(value = "对象来源'")
    private String objectId;
}
