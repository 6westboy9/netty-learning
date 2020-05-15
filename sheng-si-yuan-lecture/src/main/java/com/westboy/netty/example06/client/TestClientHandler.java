package com.westboy.netty.example06.client;

import com.westboy.netty.example06.MyDataInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author westboy
 * @date 2018-12-11
 * @since 1.0
 */
public class TestClientHandler extends SimpleChannelInboundHandler<MyDataInfo.Student> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.Student msg) {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        MyDataInfo.Student student = MyDataInfo.Student.newBuilder().setName("小王").setAge(18).setAddress("深圳").build();
        ctx.channel().writeAndFlush(student);
    }
}
