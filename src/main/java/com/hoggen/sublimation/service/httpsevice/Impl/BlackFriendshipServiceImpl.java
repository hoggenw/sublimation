package com.hoggen.sublimation.service.httpsevice.Impl;

import com.hoggen.sublimation.dao.FriendshipApplyDao;
import com.hoggen.sublimation.dao.BlackFriendshipDao;
import com.hoggen.sublimation.dto.FriendshipDTO;
import com.hoggen.sublimation.entity.BlackFriendship;
import com.hoggen.sublimation.entity.FriendshipApply;
import com.hoggen.sublimation.service.httpsevice.BlackFriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BlackFriendshipServiceImpl implements BlackFriendshipService {

    @Autowired
    private BlackFriendshipDao friendDao;

    @Autowired
    private FriendshipApplyDao applyDao;

    @Override
    public int insertFriendship(BlackFriendship blackFriendship) {
        //首先判断是不受朋友关系， 如果是要先删除
        List<FriendshipApply> friendshipApplies = applyDao.queryApplyList(blackFriendship.getUserId());
        for (FriendshipApply model: friendshipApplies) {
            if (model.getUserId().equals(blackFriendship.getUserId()) ){
                //已经存在
                if (model.getFriendId().equals(blackFriendship.getFriendId())){
                    model.setDeleteStatus(1);
                    int num =   applyDao.updateFriendship(model);
                    if (num < 0){
                        return num;
                    }
                }
            }else if (model.getFriendId().equals(blackFriendship.getUserId())){

                if (model.getUserId().equals(blackFriendship.getFriendId())){
                    model.setDeleteStatus(1);
                    int num =   applyDao.updateFriendship(model);
                    if (num < 0){
                        return num;
                    }
                }
            }
        }

        return friendDao.insertBlackFriendship(blackFriendship);
    }

    @Override
    public int deleteFriendship(BlackFriendship blackFriendship) {

        return friendDao.deleteFriendship(blackFriendship);
    }

    @Override
    public List<FriendshipDTO> queryBlackUserList(String userId) {

        return friendDao.queryBlackUserList(userId);
    }

    @Override
    public List<BlackFriendship> queryFriendShipList(String userId) {
        return friendDao.queryBlackFriendShipList(userId);
    }

    @Override
    public BlackFriendship queryBlackFriendship(String id) {
        return friendDao.queryBlackFriendship(id);
    }


}
