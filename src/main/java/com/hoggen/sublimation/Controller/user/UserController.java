package com.hoggen.sublimation.Controller.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hoggen.sublimation.util.HttpServletRequestUtil;
import com.hoggen.sublimation.util.ResponedUtils;
import dto.ReturnUserDTO;
import dto.UserInfoDTO;
import dto.UserModelDTO;
import entity.FriendshipApply;
import entity.ThirdEvidenceModel;
import entity.User;
import enums.UserStateEnum;
import httpsevice.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/api/people")
@Api(tags = "用户模块")
@Slf4j
public class UserController {

    @Reference
    private UserService userService;

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "用户查询")
    private Map<String, Object> search(HttpServletRequest request,@RequestBody UserModelDTO model) {
        String token = request.getHeader("token");
        String selfId = request.getHeader("userId");
        if (StringUtils.isEmpty(model.getName())){
            return ResponedUtils.returnCode(UserStateEnum.INFOILLEGAl.getState(),UserStateEnum.INFOILLEGAl.getStateInfo(),null);
        }
        List<User> users = userService.queryByMobileOrCodeName(model);
        return ResponedUtils.returnCode(UserStateEnum.SUCCESS.getState(),UserStateEnum.SUCCESS.getStateInfo(),users);
    }
}
