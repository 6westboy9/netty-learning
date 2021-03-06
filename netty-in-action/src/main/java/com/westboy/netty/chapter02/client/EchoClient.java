package com.westboy.netty.chapter02.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * 客户端
 *
 * @author westboy
 * @date 2018-12-03
 * @since 1.0
 */
public class EchoClient {
    private final String host;
    private final int port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("Usage: " + EchoClient.class.getSimpleName() + " <host> <port>");
            return;
        }

        String host = args[0];
        int port = Integer.parseInt(args[1]);
        new EchoClient(host, port).start();
    }

    private void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            // 创建Bootstrap
            Bootstrap b = new Bootstrap();
            // 指定EventLoopGroup以处理客户端事件
            b.group(group)
                    // 需要适用于NIO传输的Channel类型
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host, port))
                    // 在创建Channel时，向ChannelPipeline中添加一个EchoClientHandler实例
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) {
                            channel.pipeline().addLast(new EchoClientHandler());
                        }
                    });
            // 连接到远程节点，阻塞等待直到连接完成
            ChannelFuture f = b.connect().sync();

            // 阻塞，直到Channel关闭
            f.channel().closeFuture().sync();
        } finally {
            // 关闭线程池并且，释放所有的资源
            group.shutdownGracefully().sync();
        }
    }
}
