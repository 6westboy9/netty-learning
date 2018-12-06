package com.westboy.netty.chapter04;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.oio.OioServerSocketChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

/**
 * Netty实现阻塞IO服务器
 *
 * @author pengbo.wang
 * @date 2018/12/4
 * @since 1.0
 */
public class NettyOioServer {

    public void server(int port) throws Exception {
        final ByteBuf buf = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("Hi!\r\n", CharsetUtil.UTF_8));
        EventLoopGroup group = new OioEventLoopGroup();

        try {
            // 创建ServerBootstrap
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    // 使用OioEventLoopGroup以允许阻塞模式（旧的I/O）
                    .channel(OioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    // 指定 ChannelInitializer， 对于每个已接受的连接都调用它
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    // 添加一个ChannelInboundHandlerAdapter以拦截和处理事件
                                    .addLast(
                                            new ChannelInboundHandlerAdapter() {
                                                @Override
                                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                                    // 将消息写到客户端，并添加ChannelFutureListener，以便消息一被写完就关闭连接
                                                    ctx.writeAndFlush(buf.duplicate()).addListener(ChannelFutureListener.CLOSE);
                                                }
                                            });
                        }
                    });
            // 绑定服务器以接受连接
            ChannelFuture f = b.bind().sync();
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }
}
