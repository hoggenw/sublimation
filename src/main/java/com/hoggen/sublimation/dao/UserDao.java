package com.hoggen.sublimation.dao;

import com.hoggen.sublimation.entity.User;

public interface UserDao {

    /**
     * 查询用户列表并分页，可输入的条件有：
     *
     * @param userCondition
     * @param beginIndex
     * @param pageSize
     * @return
     */
//    List<ListUserModel> queryUserList(@Param("userCondition") User userCondition, @Param("rowIndex") int rowIndex,
//                                      @Param("pageSize") int pageSize);

//    /**
//     * 查询对应的用户总数
//     *
//     * @param
//     * @return
//     */
//    int queryUserCount(@Param("userCondition") User userCondition);

    /**
     * 通过用户名称 查询用户
     *
     * @param String mobile
     * @return User
     */
    User queryByUserPhone(String mobile);

    /**
     * 通过用户名称 查询用户
     *
     * @param String codeName
     * @return User
     */
    User queryByUserCodeName(String codeName);

    /**
     * 通过用户id 查询用户
     *
     * @param String name
     * @return User
     */
    User queryByUserId(String userId);

    /**
     * 插入用户
     *
     */
    int insertUser(User user);


//    /**
//     * 刷新用户
//     *
//     */
//    int updateLastLoginTime(User user);


//    /**
//     * 通过用户id 查询用户
//     *
//     * @param String name
//     * @return User
//     */
//    User queryTokenUserId(Long userId);



    /**
     * 更新用户
     *
     */
    int updateUser(User user);
//
//    /**
//     * 更新用户
//     *
//     */
//    int deleteUserId(Long userId);

}
