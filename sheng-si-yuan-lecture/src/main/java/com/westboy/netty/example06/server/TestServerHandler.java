package com.westboy.netty.example06.server;

import com.westboy.netty.example06.MyDataInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author westboy
 * @date 2018-12-11
 * @since 1.0
 */
public class TestServerHandler extends SimpleChannelInboundHandler<MyDataInfo.Student> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.Student msg) throws Exception {
        System.out.println(msg.getName());
        System.out.println(msg.getAge());
        System.out.println(msg.getAddress());
    }
}
