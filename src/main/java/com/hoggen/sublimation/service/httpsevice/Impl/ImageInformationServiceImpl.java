package com.hoggen.sublimation.service.httpsevice.Impl;


import com.hoggen.sublimation.dao.ImageInformationDao;
import com.hoggen.sublimation.entity.ImageInformation;
import com.hoggen.sublimation.enums.UpLoadEnum;
import com.hoggen.sublimation.service.httpsevice.ImageInformationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class ImageInformationServiceImpl implements ImageInformationService {

    @Autowired
    private ImageInformationDao imageInformationDao;

    private static final Logger logger = LoggerFactory.getLogger(ImageInformationServiceImpl.class);


    @Override
    public ImageInformation save(ImageInformation imageInformation, UpLoadEnum upLoadEnum) {

        // 如果是聊天图片,直接存储；如果是轮播图片,也直接存储；复诊图片,直接存储
//        if (UpLoadEnum.CHATIMG.equals(upLoadEnum) || UpLoadEnum.IMAGESHUFFLING.equals(upLoadEnum) || UpLoadEnum.RETURNVISIT.equals(upLoadEnum)) {
//
//        }

        if (imageInformation.getId() != null) {
            // 其他肖像图片
            ImageInformation i = imageInformationDao.queryByImageId(imageInformation.getId());
            // 如果找到相关信息,更新相关属性
            if (i != null) {
                int effectedNum = imageInformationDao.updateImageInformation(imageInformation);
                if (effectedNum <= 0) {
                    logger.error("CHATIMG upload fail");
                    return  null;
                }
                return imageInformation;
            }else {
                return  null;
            }
        }else {
            int effectedNum = imageInformationDao.insertImageInformation(imageInformation);
            if (effectedNum <= 0) {
                logger.error("CHATIMG upload fail");
                return  null;
            }
            return imageInformation;
        }


    }

}
