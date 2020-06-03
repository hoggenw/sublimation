package com.hoggen.sublimation.service.socketService.Impl;

import com.hoggen.sublimation.enums.CmdEnum;
import com.hoggen.sublimation.enums.ModuleEnum;
import com.hoggen.sublimation.enums.SocketTitle;
import com.hoggen.sublimation.proto.BaseMessageModel;
import com.hoggen.sublimation.proto.MessageModel;
import com.hoggen.sublimation.service.httpsevice.Impl.RedisService;
import com.hoggen.sublimation.service.socketService.MessageService;
import com.hoggen.sublimation.util.SocketResponseUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.hoggen.sublimation.util.NettyHandler.GlobalUserUtil.channelMap;


@Component
@Slf4j
public class MessageServiceImpl implements MessageService {

    @Autowired
    private RedisService redisService;


    @Override
    public void messageSendToPerson(com.google.protobuf.ByteString bytes) {
        System.out.println("person to person");
        //System.out.println("======线程22====" + Thread.currentThread().getName());

        try {
            MessageModel.YLMessageModel model = MessageModel.YLMessageModel.parseFrom(bytes);
            String  userId =  model.getFromUser().getUserId();
            BaseMessageModel.YLBaseMessageModel.Builder baseBuilder = BaseMessageModel.YLBaseMessageModel.newBuilder();
            baseBuilder.setTitle(SocketTitle.SocketTitle).setModule(ModuleEnum.COMMON_MODULE).setCommand(CmdEnum.PERSON_TO_PERSON).setData(bytes);



            ByteBuf buf2 = Unpooled.wrappedBuffer(baseBuilder.build().toByteArray());
            //判断是否在线，在线则发送，不在线存储及推送；

            Channel channal = (Channel)channelMap.get(model.getToUser().getUserId());
            if (channal != null){
                if (!redisService.ifLogin(userId,model.getToken())){
                    Channel channalSelf = (Channel)channelMap.get(userId);
                    ByteBuf byteBuf  = SocketResponseUtil.creatOotLoginResponse(userId);
                    channalSelf.writeAndFlush(new BinaryWebSocketFrame(byteBuf));
                }else {
                    channal.writeAndFlush(new BinaryWebSocketFrame(buf2));
                }


            }else {
                log.info("该账户不在线");
            }

        }catch (Exception e){
            e.printStackTrace();
        }



    }
}
