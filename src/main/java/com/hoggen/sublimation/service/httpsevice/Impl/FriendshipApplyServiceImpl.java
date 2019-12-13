package com.hoggen.sublimation.service.httpsevice.Impl;

import com.hoggen.sublimation.dao.FriendshipApplyDao;
import com.hoggen.sublimation.dao.FriendshipDao;
import com.hoggen.sublimation.dto.FriendshipDTO;
import com.hoggen.sublimation.entity.Friendship;
import com.hoggen.sublimation.entity.FriendshipApply;
import com.hoggen.sublimation.service.httpsevice.FriendshipApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
@Transactional
public class FriendshipApplyServiceImpl implements FriendshipApplyService {

    @Autowired
    private FriendshipApplyDao applyDao;


    @Override
    public int insertFriendship(FriendshipApply friendship) {
        friendship.setStatus(0);
        friendship.setDeleteStatus(0);
        friendship.setCreateTime(new Date());
        return applyDao.insertFriendship(friendship);
    }

    @Override
    public int updateFriendship(FriendshipApply friendship) {
        return applyDao.updateFriendship(friendship);
    }

    @Override
    public FriendshipDTO queryUserList(String userId) {
        return applyDao.queryUserList(userId);
    }

    @Override
    public List<FriendshipApply> queryApplyList(String userId) {
        return applyDao.queryApplyList(userId);
    }

    @Override
    public FriendshipApply queryFriendshipApply(String applyId) {
        return applyDao.queryFriendshipApply(applyId);
    }
}
