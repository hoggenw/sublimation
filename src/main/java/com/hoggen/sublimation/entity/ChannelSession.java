package com.hoggen.sublimation.entity;

import io.netty.channel.Channel;
import lombok.Data;

import java.io.Serializable;

@Data
public class ChannelSession implements Serializable {
    //Session的唯一标识
   // private String id;
    //和Session相关的channel,通过它向客户端回送数据
    private Channel channel = null;

    //快速构建一个新的Session
    public static ChannelSession buildSession(Channel channel) {
        ChannelSession session = new ChannelSession();
        session.setChannel(channel);

        //此处暂且使用netty生成的类似UUID的字符串,来标识一个session
     //   session.setId(channel.id().asLongText());
        return session;
    }

}
