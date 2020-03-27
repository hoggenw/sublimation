package com.hoggen.sublimation.dao;


import com.hoggen.sublimation.entity.ImageInformation;
import com.hoggen.sublimation.entity.User;

public interface ImageInformationDao {

    /**
     * 添加图片信息
     * @Param null
     * @Author:hoggen
     * @Date:11:16 2019-11-25
     */
    int insertImageInformation(ImageInformation imageInformation);


    /**
     * 更新图片信息
     * @Param null
     * @Author:hoggen
     * @Date:11:16 2019-11-25
     */
    int updateImageInformation(ImageInformation imageInformation);

    /**
     * 根据id获取图片
     * @Param null
     * @Author:hoggen
     * @Date:11:16 2019-11-25
     */
    ImageInformation queryByImageId(String  imageId);

}
