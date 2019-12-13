package com.hoggen.sublimation.config.nettyConfig;


import com.hoggen.sublimation.util.NettyHandler.CustomTextFrameHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;

@Component
public class NettyServiceConfig {

    private static final Logger logger = LoggerFactory.getLogger(NettyServiceConfig.class);


    private ServerSocketChannel serverSocketChannel;

    @Value("${netty.server.port}")
    public Integer port;

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    private EventLoopGroup boss = new NioEventLoopGroup();
    private EventLoopGroup work = new NioEventLoopGroup();
    //业务线程池
    private EventLoopGroup busyGroup = new NioEventLoopGroup();


    private  void  startServer(){
//服务端需要2个线程组  boss处理客户端连接  work进行客服端连接之后的处理

        ServerBootstrap bootstrap = new ServerBootstrap();
        try {
            //设置线程池
            bootstrap.group(boss, work);
            //设置socket工厂、
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.handler(new LoggingHandler(LogLevel.INFO));

            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected  void initChannel(SocketChannel socketChannel)throws Exception {
//                    //Http编解码器
                    socketChannel.pipeline().addLast(new HttpServerCodec());
                    //对写大数据流的支持
                    socketChannel.pipeline().addLast(new ChunkedWriteHandler());
                    //Http对象聚合器，，参数：消息的最大长度
                    //几乎在Netty中的编程都会使用到这个handler
                    socketChannel.pipeline().addLast(new HttpObjectAggregator(1024 * 64));
                    // 进行设置心跳检测
//                    1）readerIdleTime：为读超时时间（即多长时间没有接受到客户端发送数据）
//                    2）writerIdleTime：为写超时时间（即多长时间没有向客户端发送数据）
//                    3）allIdleTime：所有类型的超时时间
                    socketChannel.pipeline().addLast(new IdleStateHandler(30,20,80, TimeUnit.SECONDS));
                    //传输的协议 Protobuf
                    //解码用 解决分包及粘包问题
                    socketChannel.pipeline().addLast("frameDecoder", new ProtobufVarint32FrameDecoder());
                    //构造函数传递要解码成的类型
                   // socketChannel.pipeline().addLast("protobufDecoder", new ProtobufDecoder(UserMsg.YLmessageModel.getDefaultInstance()));
                    //编码用
                    socketChannel.pipeline().addLast("frameEncoder", new ProtobufVarint32LengthFieldPrepender());
                    //socketChannel.pipeline().addLast(new ProtobufEncoder());
                    //可以修改请求数据大小限制
                    //socketChannel.pipeline().addLast(new WebSocketServerProtocolHandler("/hoggen",null,false,1048576*2));
                    // 配置通道处理  来进行业务处理
                    socketChannel.pipeline().addLast(busyGroup,new CustomTextFrameHandler());
                    //System.out.println("======线程44====" + Thread.currentThread().getName());

                }
            });
            //设置参数，TCP参数
            //serverSocketchannel的设置，链接缓冲池的大小
            bootstrap.option(ChannelOption.SO_BACKLOG,1024);
            //socketchannel的设置,维持链接的活跃，清除死链接
            bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
            bootstrap.childOption(ChannelOption.TCP_NODELAY, true);//socketchannel的设置,关闭延迟发送

            //绑定端口  开启事件驱动
            logger.info("【服务器启动成功========端口："+port+"】");
            Channel channel = bootstrap.bind(port).sync().channel();
            channel.closeFuture().sync();
        }catch (Exception e){
            logger.info(e.getMessage());

        }finally{
            //关闭资源
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }

    }


//         被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器调用一次，类似于Serclet的inti()方法。被@PostConstruct修饰的方法会在构造函数之后，init()方法之前运行。

    @PostConstruct()
    public void init() {
        //需要开启一个新的线程来执行netty server 服务器
        new Thread(new Runnable() {
            @Override
            public void run() {
                startServer();
            }
        }).start();

    }

    @PreDestroy
    public void destory() throws InterruptedException {
        boss.shutdownGracefully().sync();
        work.shutdownGracefully().sync();
        logger.info("关闭Netty");
    }



























}
