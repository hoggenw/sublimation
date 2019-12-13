package com.hoggen.sublimation.service.httpsevice;

import com.hoggen.sublimation.dto.FriendshipDTO;
import com.hoggen.sublimation.entity.Friendship;

import java.util.List;


/**
 * 黑名单
 * @Author:hoggen
 * @Date:14:33 2019-12-11
 */
public interface FriendshipService {

    /**
     * 添加朋友关系
     * @Param null
     * @Author:hoggen
     * @Date:11:16 2019-11-25
     */
    int insertFriendship(Friendship friendship);


    /**
     * @Param null
     * @Author:hoggen
     * @Date:11:18 2019-11-25
     */
    int deleteFriendship(String userId, String friendId);


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
    public List<Friendship> queryFriendShipList(String userId);





}
