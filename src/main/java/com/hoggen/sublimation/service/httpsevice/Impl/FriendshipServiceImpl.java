package com.hoggen.sublimation.service.httpsevice.Impl;

import com.hoggen.sublimation.dao.FriendshipApplyDao;
import com.hoggen.sublimation.dao.FriendshipDao;
import com.hoggen.sublimation.dto.FriendshipDTO;
import com.hoggen.sublimation.entity.Friendship;
import com.hoggen.sublimation.entity.FriendshipApply;
import com.hoggen.sublimation.enums.UserStateEnum;
import com.hoggen.sublimation.service.httpsevice.FriendshipService;
import com.hoggen.sublimation.util.ResponedUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FriendshipServiceImpl implements FriendshipService {

    @Autowired
    private FriendshipDao friendDao;

    @Autowired
    private FriendshipApplyDao applyDao;

    @Override
    public int insertFriendship(Friendship friendship) {
        //首先判断是不受朋友关系， 如果是要先删除
        List<FriendshipApply> friendshipApplies = applyDao.queryApplyList(friendship.getUserId());
        for (FriendshipApply model: friendshipApplies) {
            if (model.getUserId().equals(friendship.getUserId()) ){
                //已经存在
                if (model.getFriendId().equals(friendship.getFriendId())){
                    model.setDeleteStatus(1);
                    int num =   applyDao.updateFriendship(model);
                    if (num < 0){
                        return num;
                    }
                }
            }else if (model.getFriendId().equals(friendship.getUserId())){

                if (model.getUserId().equals(friendship.getFriendId())){
                    model.setDeleteStatus(1);
                    int num =   applyDao.updateFriendship(model);
                    if (num < 0){
                        return num;
                    }
                }
            }
        }

        return friendDao.insertFriendship(friendship);
    }

    @Override
    public int deleteFriendship(String userId, String friendId) {
        return 0;
    }

    @Override
    public FriendshipDTO queryUserList(String userId) {
        return null;
    }

    @Override
    public List<Friendship> queryFriendShipList(String userId) {
        return friendDao.queryFriendShipList(userId);
    }


}
