package com.hoggen.sublimation.Controller.friend;


import com.hoggen.sublimation.dto.FriendshipDTO;
import com.hoggen.sublimation.dto.RegisterDTO;
import com.hoggen.sublimation.dto.ReturnUserDTO;
import com.hoggen.sublimation.dto.UserExecution;
import com.hoggen.sublimation.entity.Friendship;
import com.hoggen.sublimation.entity.FriendshipApply;
import com.hoggen.sublimation.entity.User;
import com.hoggen.sublimation.enums.UserStateEnum;
import com.hoggen.sublimation.service.httpsevice.FriendshipApplyService;
import com.hoggen.sublimation.service.httpsevice.FriendshipService;
import com.hoggen.sublimation.util.ResponedUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/api/apply/friend")
@Api(tags = "用户关系模块")
@Slf4j
public class FriendshipApplyController {
    @Autowired
    private FriendshipApplyService applyService;

    @Autowired
    private FriendshipService friendshipService;


    @RequestMapping(value = "/applyList", method = RequestMethod.GET)
    @ApiOperation(value = "待添加好友好友列表")
    @ResponseBody
    private Map<String, Object> applyList( HttpServletRequest request){
        String userId = request.getHeader("userId");
        FriendshipDTO friendshipDTO = applyService.queryUserList(userId);
        Map<String, Object> modelMap = new HashMap<String, Object>();
        return ResponedUtils.returnCode(UserStateEnum.SUCCESS.getState(),UserStateEnum.SUCCESS.getStateInfo(),friendshipDTO);

    }

    @RequestMapping(value = "/addBlack", method = RequestMethod.POST)
    @ApiOperation(value = "添加好友黑名单")
    @ResponseBody
    private Map<String, Object> addBlack( @Validated @RequestBody Friendship apply){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        List<Friendship> friendships = friendshipService.queryFriendShipList(apply.getUserId());
        for (Friendship model: friendships) {
            if (model.getFriendId().equals(apply.getFriendId())){
                return ResponedUtils.returnCode(UserStateEnum.FRIENDSHIPBLACKALLREADY.getState(),UserStateEnum.FRIENDSHIPBLACKALLREADY.getStateInfo(),modelMap);
            }
        }
        apply.setCreateTime(new Date());
        apply.setDeleteStatus(0);
        int effcet = friendshipService.insertFriendship(apply);
        if (effcet < 0){
            return ResponedUtils.returnCode(UserStateEnum.INNER_ERROR.getState(),UserStateEnum.INNER_ERROR.getStateInfo(),modelMap);

        }

        return ResponedUtils.returnCode(UserStateEnum.SUCCESS.getState(),UserStateEnum.SUCCESS.getStateInfo(),modelMap);



    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "好友关系确认")
    @ResponseBody
    private Map<String, Object> add( @Validated @RequestBody FriendshipApply apply){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        FriendshipApply friendshipApply = applyService.queryFriendshipApply(apply.getId());
        if (friendshipApply == null ){
            return ResponedUtils.returnCode(UserStateEnum.FRIENDSHIPEMPTY.getState(),UserStateEnum.FRIENDSHIPEMPTY.getStateInfo(),modelMap);
        }
        if (apply.getUserId().equals(friendshipApply.getUserId()) && apply.getFriendId().equals(friendshipApply.getFriendId())){
            friendshipApply.setStatus(1);
            int effect = applyService.updateFriendship(friendshipApply);
            if (effect < 0){
                //
                return ResponedUtils.returnCode(UserStateEnum.APPLYFRIENDFAILED.getState(),UserStateEnum.APPLYFRIENDFAILED.getStateInfo(),modelMap);
            }
            return ResponedUtils.returnCode(UserStateEnum.SUCCESS.getState(),UserStateEnum.SUCCESS.getStateInfo(),modelMap);
        }


        return ResponedUtils.returnCode(UserStateEnum.FRIENDSHIPILLEGAL.getState(),UserStateEnum.FRIENDSHIPILLEGAL.getStateInfo(),modelMap);



    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ApiOperation(value = "好友删除")
    @ResponseBody
    private Map<String, Object> delete( @Validated @RequestBody FriendshipApply apply)  {

        Map<String, Object> modelMap = new HashMap<String, Object>();
        FriendshipApply friendshipApply = applyService.queryFriendshipApply(apply.getId());
        if (friendshipApply == null ){
            return ResponedUtils.returnCode(UserStateEnum.FRIENDSHIPEMPTY.getState(),UserStateEnum.FRIENDSHIPEMPTY.getStateInfo(),modelMap);
        }
        if ((apply.getUserId().equals(friendshipApply.getUserId()) && apply.getFriendId().equals(friendshipApply.getFriendId()))
        || (apply.getUserId().equals(friendshipApply.getFriendId()) && apply.getFriendId().equals(friendshipApply.getUserId()))){
            friendshipApply.setDeleteStatus(1);
            int effect = applyService.updateFriendship(friendshipApply);
            if (effect < 0){
                //
                return ResponedUtils.returnCode(UserStateEnum.INNER_ERROR.getState(),UserStateEnum.INNER_ERROR.getStateInfo(),modelMap);
            }
            return ResponedUtils.returnCode(UserStateEnum.SUCCESS.getState(),UserStateEnum.SUCCESS.getStateInfo(),modelMap);
        }


        return ResponedUtils.returnCode(UserStateEnum.FRIENDSHIPILLEGAL.getState(),UserStateEnum.FRIENDSHIPILLEGAL.getStateInfo(),modelMap);


    }

    @RequestMapping(value = "/creat", method = RequestMethod.POST)
    @ApiOperation(value = "好友申请")
    @ResponseBody
    private Map<String, Object> createApply( @Validated @RequestBody FriendshipApply apply)  {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        //判断黑名单
        List<Friendship> friendships = friendshipService.queryFriendShipList(apply.getFriendId());
        for (Friendship model: friendships) {
            if (model.getFriendId().equals(apply.getUserId())){
                return ResponedUtils.returnCode(UserStateEnum.FRIENDSHIPBLACKALLREADY.getState(),UserStateEnum.FRIENDSHIPBLACKALLREADY.getStateInfo(),modelMap);
            }
        }
        //
        List<Friendship> friendships2 = friendshipService.queryFriendShipList(apply.getUserId());
        for (Friendship model: friendships2) {
            if (model.getFriendId().equals(apply.getFriendId())){
                return ResponedUtils.returnCode(UserStateEnum.FRIENDSHIPBLACKALLREADY.getState(),UserStateEnum.FRIENDSHIPBLACKALLREADY.getStateInfo(),modelMap);
            }
        }

        List<FriendshipApply> friendshipApplies = applyService.queryApplyList(apply.getUserId());
        for (FriendshipApply model: friendshipApplies) {
            if (model.getUserId().equals(apply.getUserId()) ){
                //已经存在
                if (model.getFriendId().equals(apply.getFriendId())){
                    if (model.getStatus() == 0){

                        return ResponedUtils.returnCode(UserStateEnum.APPLYALREADY.getState(),UserStateEnum.APPLYALREADY.getStateInfo(),modelMap);
                    }
                    if (model.getStatus() == 1){
                        return ResponedUtils.returnCode(UserStateEnum.AGREEALREADY.getState(),UserStateEnum.AGREEALREADY.getStateInfo(),modelMap);
                    }
                }
            }else if (model.getFriendId().equals(apply.getUserId())){

                if (model.getUserId().equals(apply.getFriendId())){
                    if (model.getStatus() == 1){
                        return ResponedUtils.returnCode(UserStateEnum.AGREEALREADY.getState(),UserStateEnum.AGREEALREADY.getStateInfo(),modelMap);
                    }
                    model.setStatus(1);
                    int effect = applyService.updateFriendship(model);
                    if (effect < 0) {
                        return ResponedUtils.returnCode(UserStateEnum.APPLYFRIENDFAILED.getState(), UserStateEnum.APPLYFRIENDFAILED.getStateInfo(), modelMap);
                    }
                    return ResponedUtils.returnCode(UserStateEnum.SUCCESS.getState(),UserStateEnum.SUCCESS.getStateInfo(),modelMap);
                }
            }
        }

        int effect = applyService.insertFriendship(apply);
        if (effect < 0){
            //
            return ResponedUtils.returnCode(UserStateEnum.APPLYFRIENDFAILED.getState(),UserStateEnum.APPLYFRIENDFAILED.getStateInfo(),modelMap);
        }

        return ResponedUtils.returnCode(UserStateEnum.SUCCESS.getState(),UserStateEnum.SUCCESS.getStateInfo(),modelMap);
    }


}
