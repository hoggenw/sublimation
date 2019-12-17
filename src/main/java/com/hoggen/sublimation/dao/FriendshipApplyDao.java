package com.hoggen.sublimation.dao;

import com.hoggen.sublimation.dto.FriendshipDTO;
import com.hoggen.sublimation.entity.FriendshipApply;

import java.util.List;

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
     * 获取已经添加成功的朋友列表
     * @Param null
     * @Author:hoggen
     * @Date:11:22 2019-11-25
     */
    public List<FriendshipDTO> queryUserList(String userId);


    /**
     * 获取未成功的朋友列表
     * @Param null
     * @Author:hoggen
     * @Date:11:22 2019-11-25
     */
    public List<FriendshipDTO> queryApplyUserList(String userId);


    /**
     * 获取所有朋友列表（包括添加成功和带添加的）
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
