package com.hoggen.sublimation.service.httpsevice;

import com.hoggen.sublimation.entity.ImageInformation;
import com.hoggen.sublimation.enums.UpLoadEnum;

public interface ImageInformationService {

    /**
     * 新增或者刷新
     * @Param null
     * @Author:hoggen
     */
    public ImageInformation save(ImageInformation imageInformation, UpLoadEnum upLoadEnum) ;
}
