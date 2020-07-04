package com.hoggen.sublimation.Controller.login;


import com.alibaba.dubbo.config.annotation.Reference;
import com.hoggen.sublimation.util.*;

import dto.*;
import entity.FriendshipApply;
import entity.ThirdEvidenceModel;
import entity.User;
import enums.LoginStateEnum;
import enums.UserStateEnum;
import httpsevice.FriendshipApplyService;
import httpsevice.LoginService;
import httpsevice.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import sun.misc.BASE64Encoder;


@Controller
@RequestMapping(value="/api/login")
@Api(tags = "用户管理模块")
@Slf4j
public class LoginController {


    @Reference
    private LoginService loginService;

    @Reference
    private UserService userService;

    @Autowired
    private Producer captchaProducer;

     @Reference
     private FriendshipApplyService applyService;



    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    @ApiOperation(value = "用户登录")
    @ResponseBody
    private Map<String, Object> userLogin(@Validated @RequestBody LoginDTO loginDTO ) {

        return loginService.userLogin(loginDTO.getPhone(),loginDTO.getPassword());

    }


    @RequestMapping(value = "/info", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "用户信息获取")
    private Map<String, Object> userInfo(HttpServletRequest request,@Validated @RequestBody UserInfoDTO userInfo) {
        String token = request.getHeader("token");
        String selfId = request.getHeader("userId");
        if (selfId.equals(userInfo.getUserId())){
            Map<String, Object> modelMap = new HashMap<String, Object>();
            User user = userService.queryByUserId(userInfo.getUserId());
            if (user == null) {
                return ResponedUtils.returnCode(UserStateEnum.EMPTY.getState(),UserStateEnum.EMPTY.getStateInfo(),"");
            }
            return ResponedUtils.returnCode(UserStateEnum.SUCCESS.getState(),UserStateEnum.SUCCESS.getStateInfo(),new ReturnUserDTO(user,1));

        } else if (userInfo.getApplyId() != null && userInfo.getApplyId().length() > 0) {
            FriendshipApply friendshipApply = applyService.queryFriendshipApply(userInfo.getApplyId() );
            if (friendshipApply == null ){
                return ResponedUtils.returnCode(UserStateEnum.FRIENDSHIPEMPTY.getState(),UserStateEnum.FRIENDSHIPEMPTY.getStateInfo(),"");
            }
            if ((friendshipApply.getFriendId().equals(userInfo.getUserId()) && friendshipApply.getUserId().equals(selfId)) || (friendshipApply.getFriendId().equals(selfId) && friendshipApply.getUserId().equals(userInfo.getUserId()))){
                Map<String, Object> modelMap = new HashMap<String, Object>();
                User user = userService.queryByUserId(userInfo.getUserId());
                if (user == null) {
                    return ResponedUtils.returnCode(UserStateEnum.EMPTY.getState(),UserStateEnum.EMPTY.getStateInfo(),"");
                }
                return ResponedUtils.returnCode(UserStateEnum.SUCCESS.getState(),UserStateEnum.SUCCESS.getStateInfo(),new ReturnUserDTO(user,2));

            }else {
                return ResponedUtils.returnCode(UserStateEnum.ROLEIILLEGAl.getState(),UserStateEnum.ROLEIILLEGAl.getStateInfo(),"");

            }

        }
        else  {
             return ResponedUtils.returnCode(UserStateEnum.APPLYINFOREERO.getState(),UserStateEnum.APPLYINFOREERO.getStateInfo(),"");

        }



    }

    @RequestMapping(value = "/pushToken", method = RequestMethod.POST)
    @ApiOperation(value = "上传或者更新pushToken")
    @ResponseBody
    private Map<String, Object> pushToken(HttpServletRequest request,@RequestBody ThirdEvidenceModel model)  {
//        String userId = HttpServletRequestUtil.getString(request,"userId");
//        String iphonePushToken = HttpServletRequestUtil.getString(request,"iphonePushToken");
        if (StringUtils.isEmpty(model.getUserId()) || StringUtils.isEmpty(model.getIphonePushToken())){
            return ResponedUtils.returnCode(UserStateEnum.APPLYINFOREERO.getState(),UserStateEnum.APPLYINFOREERO.getStateInfo(),"");
        }
        return loginService.pushToken(model);
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ApiOperation(value = "用户注册")
    @ResponseBody
    private Map<String, Object> register(HttpServletRequest request,@Validated @RequestBody RegisterDTO registerDTO)  {
        Map<String, Object> modelMap = new HashMap<String, Object>();
//        if (!CodeJudgeUtil.codeJudge(request,registerDTO.getCode())){
//            return ResponedUtils.returnCode(LoginStateEnum.CODEERROR.getState(),LoginStateEnum.CODEERROR.getStateInfo(),modelMap);
//        }
        User user = new User();
        user.setPassword(registerDTO.getPassword());
        user.setMobile(registerDTO.getPhone());
        user.setUserName(registerDTO.getName());
        user.setCodeName(registerDTO.getCodeName());
        UserExecution effect = userService.insertUser(user);
        if (effect.getState() != 0){
            return ResponedUtils.returnCode(effect.getState(),effect.getStateInfo(),modelMap);
        }
        User returnUser =  effect.getUser();
        return ResponedUtils.returnCode(effect.getState(),effect.getStateInfo(),new ReturnUserDTO(returnUser,1));
    }

    @RequestMapping(value = "/quit", method = RequestMethod.POST)
    @ApiOperation(value = "用户退出登录")
    @ResponseBody
    private Map<String, Object> quit(@Validated @RequestBody QuitDTO quitDTO)  {

        return loginService.quit(quitDTO);
    }


    @ApiOperation(value = "使用旧密码更新密码")
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> updatePassword(HttpServletRequest request,@Validated @RequestBody UpdatePasswordDTO updatePasswordDTO) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
//        String newPassword = HttpServletRequestUtil.getString(request,"newPassword");
//        String oldPassword = HttpServletRequestUtil.getString(request,"oldPassword");
//        if (newPassword == null ||  oldPassword == null) {
//            return ResponedUtils.returnCode(LoginStateEnum.INNERERROR.getState(),LoginStateEnum.INNERERROR.getStateInfo(),modelMap);
//        }
        String userId = request.getHeader("userId");
        User user = userService.queryByUserId(userId);
        if (user == null || user.getStatus() == 0) {
            return ResponedUtils.returnCode(LoginStateEnum.EMPTY.getState(),LoginStateEnum.EMPTY.getStateInfo(),modelMap);

        }
        if (user != null && user.getPassword() != null) {
            if ((MD5Util.MD5Encode(updatePasswordDTO.getOldPassword() + user.getRandomString())).equals(user.getPassword())) {
                User newPasswordUser = new User();
                newPasswordUser.setUserId(userId);
                newPasswordUser.setPassword(updatePasswordDTO.getNewPassword());
                newPasswordUser.setUpdateTime(new Date());
                UserExecution effect = userService.modifyUser(newPasswordUser);
                return ResponedUtils.returnCode(effect.getState(),effect.getStateInfo(),modelMap);

            }
            else  {
                return ResponedUtils.returnCode(LoginStateEnum.OLDPASSWORDERROR.getState(),LoginStateEnum.OLDPASSWORDERROR.getStateInfo(),modelMap);
            }
        }else {
            return ResponedUtils.returnCode(LoginStateEnum.EMPTY.getState(),LoginStateEnum.EMPTY.getStateInfo(),modelMap);
        }

    }
    @ApiOperation(value = "更新头像")
    @RequestMapping(value = "/updateAvatar", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> updateAvatar(HttpServletRequest request,@Validated @RequestBody UpdateAvatarDTO updateAvatarDTO) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        String userId = request.getHeader("userId");
        User user = userService.queryByUserId(userId);
        if (user == null || user.getStatus() == 0) {
            return ResponedUtils.returnCode(LoginStateEnum.EMPTY.getState(),LoginStateEnum.EMPTY.getStateInfo(),modelMap);
        }
        User userAvartar = new User();
        userAvartar.setUserId(userId);
        userAvartar.setAvatar(updateAvatarDTO.getAvatar());

        UserExecution effect = userService.modifyUser(userAvartar);

        return ResponedUtils.returnCode(effect.getState(),effect.getStateInfo(),modelMap);
    }


    /**
     * 生成验证码
     * @param request
     * @param response
     * @throws Exception
     */
    @ApiOperation(value = "获取注册验证码")
    @RequestMapping(value = "/getKaptchaImage", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object>  getKaptchaImage(HttpServletRequest request,
                                HttpServletResponse response) throws Exception {

        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        // 生产验证码字符串并保存到session中
        String createText = captchaProducer.createText();
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, createText);
        // 使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
        BufferedImage challenge = captchaProducer.createImage(createText);
        ImageIO.write(challenge, "png", jpegOutputStream);
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("img", encoder.encode(jpegOutputStream.toByteArray()));
        return ResponedUtils.returnCode(LoginStateEnum.SUCCESS.getState(),LoginStateEnum.SUCCESS.getStateInfo(),modelMap);

    }


}
