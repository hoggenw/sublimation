package com.hoggen.sublimation.service.httpsevice;


import com.hoggen.sublimation.dto.UserExecution;
import com.hoggen.sublimation.entity.User;

public interface UserService {
    /**
     * 通过用户名称 查询用户
     *
     * @param
     * @return User
     */
    User queryByUserName(String mobile);

    /**
     * 通过用户id 查询用户
     *
     * @param
     * @return User
     */
    User queryByUserId(String userId);


    /**
     * 插入用户
     *
     */
    UserExecution insertUser(User user);


    /**
     * 修改用户信息 type 1 password 2.冻结 解冻
     */
    UserExecution modifyUser(User user);


    /**
     * 更新用户
     *
     */
    int updateUser(User user);


}
