package com.hoggen.sublimation.service.httpsevice.Impl;


import com.hoggen.sublimation.dao.UserDao;
import com.hoggen.sublimation.dto.QuitDTO;
import com.hoggen.sublimation.dto.ReturnUserDTO;
import com.hoggen.sublimation.entity.User;
import com.hoggen.sublimation.enums.LoginStateEnum;
import com.hoggen.sublimation.service.httpsevice.LoginService;
import com.hoggen.sublimation.service.httpsevice.UserService;
import com.hoggen.sublimation.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

     @Autowired
    private RedisService redisService;



    private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);


    @Override
    public Map<String, Object> userLogin(String phone,String password) {
        logger.info("userLoginJudge");
        // TODO Auto-generated method stub
        User user = userService.queryByUserName(phone);
        Map<String, Object> modelMap = new HashMap<String, Object>();
        Map<String, Object> modelMapData = new HashMap<String, Object>();
        if (user != null && user.getStatus() == 1) {
            return ResponedUtils.returnCode(LoginStateEnum.EMPTY.getState(),LoginStateEnum.EMPTY.getStateInfo(),modelMapData);
        }
        if (user != null && user.getPassword() != null) {
            if ((MD5Util.MD5Encode(password + user.getRandomString())).equals(user.getPassword())) {

                modelMap.put("errno", LoginStateEnum.SUCCESS.getState());
                modelMap.put("errmsg", LoginStateEnum.SUCCESS.getStateInfo());
                String token = JwtUtil.sign(phone, String.valueOf(user.getUserId()),String.valueOf(user.getRoleType()));

                String returnToken =  redisService.saveLoginStatus( String.valueOf(user.getUserId()),token);

                modelMapData.put("token",returnToken);
                modelMapData.put("user",new ReturnUserDTO(user));

                User user1 = new User();
                user1.setLastLoginTime(new Date());

                userService.updateUser(user);

                return ResponedUtils.returnCode(LoginStateEnum.SUCCESS.getState(),LoginStateEnum.SUCCESS.getStateInfo(),modelMapData);


            } else {

                return ResponedUtils.returnCode(LoginStateEnum.USERNONE.getState(),LoginStateEnum.USERNONE.getStateInfo(),modelMapData);
            }

        } else {
            return ResponedUtils.returnCode(LoginStateEnum.EMPTY.getState(),LoginStateEnum.EMPTY.getStateInfo(),modelMapData);

        }

    }



    @Override
    public Map<String, Object> userInfo(HttpServletRequest request) {
        logger.info("userInfo");
        Map<String, Object> modelMap = new HashMap<String, Object>();
        Map<String, Object> modelMapData = new HashMap<String, Object>();
        String token = request.getHeader("token");
        // 根据账号  查找运输人id
        User user = userService.queryByUserId(JwtUtil.getLoginUserID(token));
        if(user != null ){

            modelMapData.put("roleType",user.getRoleType());
            modelMapData.put("userId",user.getUserId());
            modelMapData.put("token",token);

        }else {
            return ResponedUtils.returnCode(LoginStateEnum.EMPTY.getState(), LoginStateEnum.EMPTY.getStateInfo(), new HashMap<>());
        }


        modelMap.put("errno", "0");
        modelMap.put("errmsg", "操作成功");
        modelMap.put("data", modelMapData);
        return modelMap;
    }

    @Override
    public Map<String, Object> quit(QuitDTO quitDTO) {

        if (redisService.quitLogin(quitDTO.getUserId())){
            return ResponedUtils.returnCode(LoginStateEnum.SUCCESS.getState(), LoginStateEnum.SUCCESS.getStateInfo(), new HashMap<>());
        }
        return ResponedUtils.returnCode(LoginStateEnum.QUITERROR.getState(), LoginStateEnum.QUITERROR.getStateInfo(), new HashMap<>());

    }


}
