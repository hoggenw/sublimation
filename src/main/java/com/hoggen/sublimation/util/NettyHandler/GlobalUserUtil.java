package com.hoggen.sublimation.util.NettyHandler;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.HashMap;
import java.util.Map;

public class GlobalUserUtil {

    //保存全局的  连接上服务器的客户

    //保存全局的  连接上服务器的客户
    public static  Map<String, Object> channelMap = new HashMap<String, Object>();

}
