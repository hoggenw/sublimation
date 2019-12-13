package com.hoggen.sublimation.service.httpsevice.Impl;


import com.hoggen.sublimation.dao.UserDao;
import com.hoggen.sublimation.dto.UserExecution;
import com.hoggen.sublimation.entity.User;
import com.hoggen.sublimation.enums.UserStateEnum;
import com.hoggen.sublimation.service.httpsevice.UserService;
import com.hoggen.sublimation.util.MD5Util;
import com.hoggen.sublimation.util.SensitiveFilter.WordFilter;
import com.hoggen.sublimation.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao rUserDao;

    @Override
    public User queryByUserName(String mobile) {
        User user = null;
        user = rUserDao.queryByUserPhone(mobile);
        return user;
    }

    @Override
    public User queryByUserId(String userId) {
        User user = null;
        user = rUserDao.queryByUserId(userId);
        return user;
    }

    @Override
    public UserExecution insertUser(User user) {

        if (user != null && user.getUserName() != null && user.getPassword() != null && user.getCodeName() != null) {
            if (WordFilter.containRubbish(user.getUserName())){
                return new UserExecution(UserStateEnum.NAMESensitive);
            }
            if (WordFilter.containRubbish(user.getCodeName())){
                return new UserExecution(UserStateEnum.CODENAMESensitive);
            }
            User tempUser = rUserDao.queryByUserPhone(user.getMobile());
            if (tempUser != null){
                return new UserExecution(UserStateEnum.ALREADYPHONE);
            }

            User tempUser2 = rUserDao.queryByUserCodeName(user.getCodeName());
            if (tempUser2 != null){
                return new UserExecution(UserStateEnum.ALREADYCODENAME);
            }

            user.setCreateTime(new Date());
            user.setStatus(1);
            user.setRoleType(0);
            String randomString = StringUtil.getRandomString(8);
            String storePassString = MD5Util.MD5Encode(user.getPassword() + randomString);
            user.setPassword(storePassString);
            user.setRandomString(randomString);;
            user.setUpdateTime(new Date());
            try {
                int effectedNum = rUserDao.insertUser(user);
                if (effectedNum <= 0) {
                    logger.error("addUser fail");
                    throw new RuntimeException("添加用户失败");
                }
            } catch (Exception e) {
                logger.error("addUser fail :" + e.getMessage());
                throw new RuntimeException("添加用户失败" + e.toString());
            }
            return new UserExecution(UserStateEnum.SUCCESS, user);
        } else {
            return new UserExecution(UserStateEnum.EMPTY);
        }
    }

    @Override
    public UserExecution modifyUser(User user) {
        User userSql = queryByUserId(user.getUserId());
        if (userSql == null) {
            return new UserExecution(UserStateEnum.EMPTY);
        }

        if (user != null && user.getUserId()  != null  ) {
            try {
                if(user.getPassword() != null){
                    String randomString = StringUtil.getRandomString(8);
                    String storePassString = MD5Util.MD5Encode(user.getPassword() + randomString);
                    user.setPassword(storePassString);
                    user.setRandomString(randomString);
                }

                int effectedNum = rUserDao.updateUser(user);
                if (effectedNum <= 0) {
                    logger.error(" modify User Password fail");
                    throw new RuntimeException("修改用户失败");
                }
            } catch (Exception e) {
                logger.error("modify User Password fail :" + e.getMessage());
                throw new RuntimeException("修改用户失败" + e.toString());
            }

            return new UserExecution(UserStateEnum.SUCCESS, user);
        } else {
            return new UserExecution(UserStateEnum.EMPTY);
        }


    }

    @Override
    public int updateUser(User user) {
        return rUserDao.updateUser(user);
    }


}
