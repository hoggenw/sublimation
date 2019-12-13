package com.hoggen.sublimation.dao;

import com.hoggen.sublimation.dto.FriendshipDTO;
import com.hoggen.sublimation.dto.ReturnUserDTO;
import com.hoggen.sublimation.entity.Friendship;
import com.hoggen.sublimation.entity.FriendshipApply;

import java.util.List;
import java.util.Map;

public interface FriendshipApplyDao {
    /**
     * 添加朋友关系
     * @Param null
     * @Author:hoggen
     * @Date:11:16 2019-11-25
     */
    int insertFriendship(FriendshipApply friendship);


    /**
     * 更新朋友关系
     * @Param null
     * @Author:hoggen
     * @Date:11:16 2019-11-25
     */
    int updateFriendship(FriendshipApply friendship);


//    /**
//     * @Param null
//     * @Author:hoggens
//     * @Date:11:18 2019-11-25
//     */
//    int deleteFriendship(String userId, String friendId);


    /**
     * 获取朋友列表
     * @Param null
     * @Author:hoggen
     * @Date:11:22 2019-11-25
     */
    public FriendshipDTO queryUserList(String userId);


    /**
     * 获取朋友列表
     * @Param null
     * @Author:hoggen
     * @Date:11:22 2019-11-25
     */
    public List<FriendshipApply> queryApplyList(String userId);


    /**
     * 获取朋友
     * @Param null
     * @Author:hoggen
     * @Date:11:22 2019-11-25
     */
    public FriendshipApply  queryFriendshipApply(String applyId);



}
