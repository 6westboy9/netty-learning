package com.westboy.netty.second.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author westboy
 * @date 2018-12-04
 * @since 1.0
 */
public class EchoServerHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("from client(" + channel.remoteAddress() + "): " + msg);

        String returnMsg = "Welcome!";
        channel.writeAndFlush(returnMsg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
