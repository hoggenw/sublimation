package com.hoggen.sublimation.Controller.friend;



import com.alibaba.dubbo.config.annotation.Reference;
import com.hoggen.sublimation.util.ResponedUtils;
import dto.FriendshipDTO;
import dto.UpdateUserCategoryDTO;
import entity.BlackFriendship;
import entity.FriendshipApply;
import enums.UserStateEnum;
import httpsevice.BlackFriendshipService;
import httpsevice.FriendshipApplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
    @Reference
    private FriendshipApplyService applyService;

    @Reference
    private BlackFriendshipService blackFriendshipService;



    @RequestMapping(value = "/addBlack", method = RequestMethod.POST)
    @ApiOperation(value = "添加好友黑名单")
    @ResponseBody
    private Map<String, Object> addBlack(HttpServletRequest request, @Validated @RequestBody BlackFriendship apply){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        String userId = request.getHeader("userId");
        if (!apply.getUserId().equals(userId)){
            return ResponedUtils.returnCode(UserStateEnum.APPLYINFOREERO.getState(),UserStateEnum.APPLYINFOREERO.getStateInfo(),modelMap);

        }
        List<BlackFriendship> blackFriendships = blackFriendshipService.queryFriendShipList(apply.getUserId());
        for (BlackFriendship model: blackFriendships) {
            if (model.getFriendId().equals(apply.getFriendId())){
                return ResponedUtils.returnCode(UserStateEnum.FRIENDSHIPBLACKALLREADY.getState(),UserStateEnum.FRIENDSHIPBLACKALLREADY.getStateInfo(),modelMap);
            }
        }
        apply.setCreateTime(new Date());
        apply.setDeleteStatus(0);
        int effcet = blackFriendshipService.insertFriendship(apply);
        if (effcet < 0){
            return ResponedUtils.returnCode(UserStateEnum.INNER_ERROR.getState(),UserStateEnum.INNER_ERROR.getStateInfo(),modelMap);

        }

        return ResponedUtils.returnCode(UserStateEnum.SUCCESS.getState(),UserStateEnum.SUCCESS.getStateInfo(),modelMap);

    }

    @RequestMapping(value = "/blackList", method = RequestMethod.GET)
    @ApiOperation(value = "黑名单列表")
    @ResponseBody
    private Map<String, Object> blackList(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        String userId = request.getHeader("userId");

        List<FriendshipDTO>  blackFriendships = blackFriendshipService.queryBlackUserList(userId);

        return ResponedUtils.returnCode(UserStateEnum.SUCCESS.getState(),UserStateEnum.SUCCESS.getStateInfo(),blackFriendships);

    }

    @RequestMapping(value = "/blackDelete", method = RequestMethod.POST)
    @ApiOperation(value = "移除黑名单中的某个对象")
    @ResponseBody
    private Map<String, Object> blackDelete(HttpServletRequest request,@Validated @RequestBody BlackFriendship blackFriendship){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        String userId = request.getHeader("userId");
        if (!blackFriendship.getUserId().equals(userId)){
            return ResponedUtils.returnCode(UserStateEnum.APPLYINFOREERO.getState(),UserStateEnum.APPLYINFOREERO.getStateInfo(),modelMap);

        }
        BlackFriendship blackship =  blackFriendshipService.queryBlackFriendship(blackFriendship.getId());
        if (blackship == null ){
            return ResponedUtils.returnCode(UserStateEnum.BLACKNULL.getState(),UserStateEnum.BLACKNULL.getStateInfo(),modelMap);
        }

        blackship.setDeleteStatus(1);
        int effect = blackFriendshipService.deleteFriendship(blackship);
        if (effect < 0){
            //
            return ResponedUtils.returnCode(UserStateEnum.INNER_ERROR.getState(),UserStateEnum.INNER_ERROR.getStateInfo(),modelMap);
        }

        return ResponedUtils.returnCode(UserStateEnum.SUCCESS.getState(),UserStateEnum.SUCCESS.getStateInfo(),modelMap);

    }


    @RequestMapping(value = "/applyList", method = RequestMethod.GET)
    @ApiOperation(value = "待添加好友好友列表")
    @ResponseBody
    private Map<String, Object> applyList( HttpServletRequest request){
        String userId = request.getHeader("userId");

        List<FriendshipDTO> friendshipDTOs = applyService.queryApplyUserList(userId);
        Map<String, Object> modelMap = new HashMap<String, Object>();

        return ResponedUtils.returnCode(UserStateEnum.SUCCESS.getState(),UserStateEnum.SUCCESS.getStateInfo(),friendshipDTOs);

    }

    @RequestMapping(value = "/friedsList", method = RequestMethod.GET)
    @ApiOperation(value = "好友列表")
    @ResponseBody
    private Map<String, Object> freidsList( HttpServletRequest request){
        String userId = request.getHeader("userId");
        List<FriendshipDTO> friendshipDTOs = applyService.queryUserList(userId);
        Map<String, Object> modelMap = new HashMap<String, Object>();
        return ResponedUtils.returnCode(UserStateEnum.SUCCESS.getState(),UserStateEnum.SUCCESS.getStateInfo(),friendshipDTOs);

    }

    @RequestMapping(value = "/updateFriendship", method = RequestMethod.POST)
    @ApiOperation(value = "更新备注名及备注信息")
    @ResponseBody
    private Map<String, Object> categoryName(HttpServletRequest request, @Validated @RequestBody UpdateUserCategoryDTO apply){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        String userId = request.getHeader("userId");
        FriendshipApply friendshipApply = applyService.queryFriendshipApply(apply.getId());

        if (friendshipApply == null ){
            return ResponedUtils.returnCode(UserStateEnum.FRIENDSHIPEMPTY.getState(),UserStateEnum.FRIENDSHIPEMPTY.getStateInfo(),modelMap);
        }
        if (friendshipApply.getUserId().equals(userId) ){
            friendshipApply.setFirendCategoryName(apply.getName());
            friendshipApply.setUserRemark(apply.getRemark());

        }else if  (friendshipApply.getFriendId().equals(userId) ){
            friendshipApply.setUserCategoryName(apply.getName());
            friendshipApply.setFriendRemark(apply.getRemark());

        }else {
            return ResponedUtils.returnCode(UserStateEnum.OFFLINE.getState(),UserStateEnum.OFFLINE.getStateInfo(),modelMap);

        }
        if (apply.getRemark() != null && apply.getRemark().length() <=0 && apply.getName() != null && apply.getName().length() <= 0 ){
            return ResponedUtils.returnCode(UserStateEnum.INFOILLEGAl.getState(),UserStateEnum.INFOILLEGAl.getStateInfo(),modelMap);

        }
        int effcet = applyService.updateFriendship(friendshipApply);
        if (effcet < 0){
            return ResponedUtils.returnCode(UserStateEnum.INNER_ERROR.getState(),UserStateEnum.INNER_ERROR.getStateInfo(),modelMap);

        }

        return ResponedUtils.returnCode(UserStateEnum.SUCCESS.getState(),UserStateEnum.SUCCESS.getStateInfo(),modelMap);

    }



    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "好友关系确认")
    @ResponseBody
    private Map<String, Object> add(HttpServletRequest request, @Validated @RequestBody FriendshipApply apply){
        Map<String, Object> modelMap = new HashMap<String, Object>();

        String userId = request.getHeader("userId");
        if (!apply.getFriendId().equals(userId)){
            return ResponedUtils.returnCode(UserStateEnum.APPLYINFOREERO.getState(),UserStateEnum.APPLYINFOREERO.getStateInfo(),modelMap);
        }
        FriendshipApply friendshipApply = applyService.queryFriendshipApply(apply.getId());
        if (friendshipApply == null ){
            return ResponedUtils.returnCode(UserStateEnum.FRIENDSHIPEMPTY.getState(),UserStateEnum.FRIENDSHIPEMPTY.getStateInfo(),modelMap);
        }
        if (apply.getUserId().equals(friendshipApply.getUserId()) && apply.getFriendId().equals(friendshipApply.getFriendId())){
            apply.setStatus(1);
            int effect = applyService.updateFriendship(apply);
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
    private Map<String, Object> delete(HttpServletRequest request,@Validated @RequestBody FriendshipApply apply)  {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        String userId = request.getHeader("userId");
        if (!(apply.getUserId().equals(userId))){
            return ResponedUtils.returnCode(UserStateEnum.APPLYINFOREERO.getState(),UserStateEnum.APPLYINFOREERO.getStateInfo(),modelMap);

        }
        FriendshipApply friendshipApply = applyService.queryFriendshipApply(apply.getId());
        if (friendshipApply == null ){
            return ResponedUtils.returnCode(UserStateEnum.FRIENDSHIPEMPTY.getState(),UserStateEnum.FRIENDSHIPEMPTY.getStateInfo(),modelMap);
        }
        if ((apply.getUserId().equals(friendshipApply.getUserId()) && apply.getFriendId().equals(friendshipApply.getFriendId()))
        || (apply.getUserId().equals(friendshipApply.getFriendId()) && apply.getFriendId().equals(friendshipApply.getUserId()))){
            friendshipApply.setDeleteStatus(1);
            int effect = applyService.deleteFriendship(friendshipApply);
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
    private Map<String, Object> createApply( HttpServletRequest request, @Validated @RequestBody FriendshipApply apply)  {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        String userId = request.getHeader("userId");
        if (!apply.getUserId().equals(userId)){
            return ResponedUtils.returnCode(UserStateEnum.APPLYINFOREERO.getState(),UserStateEnum.APPLYINFOREERO.getStateInfo(),modelMap);

        }
        //判断黑名单
        List<BlackFriendship> blackFriendships = blackFriendshipService.queryFriendShipList(apply.getFriendId());
        for (BlackFriendship model: blackFriendships) {
            if (model.getFriendId().equals(apply.getUserId())){
                return ResponedUtils.returnCode(UserStateEnum.FRIENDSHIPBLACKALLREADY.getState(),UserStateEnum.FRIENDSHIPBLACKALLREADY.getStateInfo(),modelMap);
            }
        }
        //
        List<BlackFriendship> friendships2 = blackFriendshipService.queryFriendShipList(apply.getUserId());
        for (BlackFriendship model: friendships2) {
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
