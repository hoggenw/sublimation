package com.hoggen.sublimation.service.socketService;

import com.hoggen.sublimation.annotion.SocketCmd;
import com.hoggen.sublimation.annotion.SocketModule;
import com.hoggen.sublimation.enums.CmdEnum;
import com.hoggen.sublimation.enums.ModuleEnum;

@SocketModule(module = ModuleEnum.COMMON_MODULE)
public interface MessageService {

    /**
     *  单对单发送消息
     */
    @SocketCmd(cmd = CmdEnum.PERSON_TO_PERSON)
    public  void messageSendToPerson( com.google.protobuf.ByteString bytes);


}
