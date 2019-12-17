package com.hoggen.sublimation.dao;

import com.hoggen.sublimation.dto.FriendshipDTO;
import com.hoggen.sublimation.entity.BlackFriendship;

import java.util.List;

/**
 * 黑名单
 * @Author:hoggen
 * @Date:14:33 2019-12-11
 */
public interface BlackFriendshipDao {

    /**
     * 添加朋友关系
     * @Param null
     * @Author:hoggen
     * @Date:11:16 2019-11-25
     */
    int insertBlackFriendship(BlackFriendship blackFriendship);


    /**
     * @Param null
     * @Author:hoggen
     * @Date:11:18 2019-11-25
     */
    int deleteFriendship(BlackFriendship blackFriendship);


    /**
     * 获取黑名单列表
     * @Param null
     * @Author:hoggen
     * @Date:11:22 2019-11-25
     */
    public List<FriendshipDTO> queryBlackUserList(String userId);



    /**
     * 获取朋友列表
     * @Param null
     * @Author:hoggen
     * @Date:11:22 2019-11-25
     */
    public List<BlackFriendship> queryBlackFriendShipList(String userId);




    /**
     * 获取朋友
     * @Param null
     * @Author:hoggen
     * @Date:11:22 2019-11-25
     */
    public BlackFriendship  queryBlackFriendship(String id);


    
}
