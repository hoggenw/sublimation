package com.hoggen.sublimation.Controller.file;


import com.alibaba.dubbo.config.annotation.Reference;
import com.hoggen.sublimation.util.ResponedUtils;

import entity.ImageInformation;
import enums.UpLoadEnum;
import enums.UserStateEnum;
import httpsevice.ImageInformationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

@RestController
@RequestMapping("/api/file")
@Slf4j
@Api(tags = "图片接口")
@Validated
public class UpLoadController {

    @Value("${upload-config.department-path}")
    private String departmentPath;


    @Value("${upload-config.module-path}")
    private String modulePath;

    @Value("${upload-config.doctor-portrait-path}")
    private String doctorPortraitPath;
    //upload-config.doctor-qrcode-path=ihosdev/doctorqrcode
    //upload-config.nurse-qrcode-path=ihosdev/nurseqrcode
    //upload-config.coupon-qrcode-path=ihosdev/couponqrcode
    //upload-config.organization-qrcode-path=ihosdev/organizationqrcode
    @Value("${upload-config.user-portrait-path}")
    private String userPath;


    @Value("${upload-config.hospital-path}")
    private String hospitalPath;
    @Value("${upload-config.chat-path}")
    private String chatPath;

//    upload-config.public-access-path=http://inthosfiles.rc.schlwyy.com

    @Value("${upload-config.nurse-portrait-path}")
    private String nursePath;

//    upload-config.channel-qrcode-path=ihosdev/channelqrcode

    @Value("${upload-config.image-shuffling-path}")
    private String imageShufflingPath;

    @Value("${upload-config.return-visit-path}")
    private String returnVisitPath;

    @Value("${upload-config.multi-send-meesgae}")
    private String multiSendMeesgae;


    @Value("${upload-config.first-diagnosis-path}")
    private String firstDiagnosisTextPath;



    @Value("${qiniu-cloud.bucket}")
    private String bucket;

    @Value("${qiniu-cloud.domain}")
    private String domain;

    @Autowired
    private UploadManager uploadManager;

    @Autowired private Auth auth;

    @Reference
    private ImageInformationService imageInformationService;


    @RequestMapping(value = "/uploadToken", method = RequestMethod.POST)
    @ApiOperation(value = "上传图片")
    @ResponseBody
    public Map<String, Object> uploadToken() {

        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("uploadToken",auth.uploadToken(bucket));
        return ResponedUtils.returnCode(UserStateEnum.SUCCESS.getState(), UserStateEnum.SUCCESS.getStateInfo(), modelMap);

    }



    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ApiOperation(value = "上传图片")
    @ResponseBody
    public Map<String, Object> upLoadImg(
            MultipartFile file,
            @RequestParam(value = "type") @ApiParam(value = "上传位置", required = true)
                    UpLoadEnum upLoadEnum,
            @RequestParam(value = "object-id") @ApiParam(value = "对象来源", required = true) String objectId,
            @RequestParam(value = "imageId") @ApiParam(value = "图片id", required = false) String imageId) {
        System.out.println("获取信息" + departmentPath);

        Map<String, Object> modelMap = new HashMap<String, Object>();
        ImageInformation imageInformation = new ImageInformation();
        if (file == null) {
            return ResponedUtils.returnCode(UserStateEnum.FILLNULL.getState(), UserStateEnum.FILLNULL.getStateInfo(), modelMap);

        }
        if (imageId != null && imageId.length() > 0){
            imageInformation.setId(imageId);
        }

        // 文件类型
        String contentType = file.getContentType();
        // 获取原始文件名
        String filename = file.getOriginalFilename();
        String savePath = null;
        String middlePath = null;
        // 重新生成文件名
        String uuid = UUID.randomUUID().toString().replace("-", "");
        // 获取扩展名
        assert filename != null;
        String prefix = filename.substring(filename.lastIndexOf(".") + 1);
        String newFileName = uuid + "." + prefix;


        // 获取存储路径(根据不同的上传图片地方,将图片放入不同的文件夹)
        // 判断该路径下文件夹是否存在,没有就创建
        switch (upLoadEnum) {
            case DEPARTMENTIMG:
                savePath = departmentPath + "/" + newFileName;

                break;
            case HOSPITALIMG:
                savePath = hospitalPath + "/" + newFileName;

                break;
            case CHATIMG:
                savePath = chatPath + "/" + newFileName;

                break;
            case DOCTORPORTRAIT:

                savePath = doctorPortraitPath + "/" + newFileName;

                break;
            case USERPORTRAIT:
                savePath = userPath + "/" + newFileName;

                break;
            case NURSEPORTRAIT:
                savePath = nursePath + "/" + newFileName;

                break;
            case IMAGESHUFFLING:
                savePath = imageShufflingPath + "/" + newFileName;

                break;

            case MODULE:
                savePath = modulePath + "/" + newFileName;

                break;
            case RETURNVISIT:
                savePath = returnVisitPath + "/" + newFileName;
                break;
            case MULTI_SEND_MEESGAE:
                savePath = multiSendMeesgae + "/" + newFileName;
                break;
            case FIRST_DIAGNOSIS_TEXT:
                savePath = firstDiagnosisTextPath + "/" + newFileName;
                break;
            default:
                return ResponedUtils.returnCode(UserStateEnum.FILETYPEUNKONWE.getState(), UserStateEnum.FILETYPEUNKONWE.getStateInfo(), modelMap);

        }
        imageInformation.setCreateDate(new Date());
        imageInformation.setPrefix(prefix);
        imageInformation.setFileName(newFileName);
        imageInformation.setFormerName(filename);
        // 避免重复
        imageInformation.setObjectId(objectId + upLoadEnum);
        imageInformation.setLastModifiedDate(new Date());

        try {
            String filePath = null;
            String publicAccess = domain;
            filePath = publicAccess + "/" + savePath; // 公网访问地址
            imageInformation.setFilePath(filePath);
            Response response =
                    uploadManager.put(
                            file.getBytes(), savePath, auth.uploadToken(bucket));
            log.info("文件上传成功:七牛云返回信息:{}", response.bodyString());
            ImageInformation result = imageInformationService.save(imageInformation, upLoadEnum);
            if (result == null){
                if (imageId != null && imageId.length() > 0){
                    return ResponedUtils.returnCode(UserStateEnum.FILEUPDATENONE.getState(), UserStateEnum.FILEUPDATENONE.getStateInfo(), modelMap);

                }
                return ResponedUtils.returnCode(UserStateEnum.INNER_ERROR.getState(), UserStateEnum.INNER_ERROR.getStateInfo(), modelMap);

            }
            // 保存图片相关信息
            modelMap.put("path", result.getFilePath());
            modelMap.put("imageId", result.getId());

        } catch (Exception e) {
            System.out.println(e);
            log.error("图片上传失败:" + e.getMessage());
            return ResponedUtils.returnCode(UserStateEnum.INNER_ERROR.getState(), UserStateEnum.INNER_ERROR.getStateInfo(), modelMap);
        }

        return ResponedUtils.returnCode(UserStateEnum.SUCCESS.getState(), UserStateEnum.SUCCESS.getStateInfo(), modelMap);

    }

}
