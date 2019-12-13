package com.hoggen.sublimation.util;

import com.hoggen.sublimation.enums.CmdEnum;
import com.hoggen.sublimation.enums.ModuleEnum;
import com.hoggen.sublimation.enums.SocketTitle;
import com.hoggen.sublimation.proto.BaseMessageModel;
import com.hoggen.sublimation.proto.MessageModel;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class SocketResponseUtil {

    public static ByteBuf creatOotLoginResponse (String userId) {

        MessageModel.YLMessageModel.Builder builder =  MessageModel.YLMessageModel.newBuilder();
        builder.setTextString("请登录").setMessageType(2);
        MessageModel.YLMessageModel model2 = builder.build();

        BaseMessageModel.YLBaseMessageModel.Builder baseBuilder = BaseMessageModel.YLBaseMessageModel.newBuilder();
        baseBuilder.setTitle(SocketTitle.SocketTitle).setModule(ModuleEnum.COMMON_MODULE).setCommand(CmdEnum.MISS_LOGIN).setData(model2.toByteString());



        ByteBuf buf2 = Unpooled.wrappedBuffer(baseBuilder.build().toByteArray());
        return  buf2;
    }
}
