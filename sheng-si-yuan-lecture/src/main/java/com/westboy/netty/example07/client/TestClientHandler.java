package com.westboy.netty.example07.client;

import com.westboy.netty.example07.MyDataInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

/**
 * @author westboy
 * @date 2018-12-11
 * @since 1.0
 */
public class TestClientHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        int randomInt = new Random().nextInt(3);
        System.out.println(randomInt);
        MyDataInfo.MyMessage myMessage;

        if (0 == randomInt) {
            myMessage = MyDataInfo.MyMessage.newBuilder().
                    setDataType(MyDataInfo.MyMessage.DataType.PersonType).
                    setPerson(MyDataInfo.Person.newBuilder().
                            setName("zhangsan").
                            setAge(20).
                            setAddress("beijing").
                            build()).
                    build();
        } else if (1 == randomInt) {
            myMessage = MyDataInfo.MyMessage.newBuilder().
                    setDataType(MyDataInfo.MyMessage.DataType.DogType).
                    setDog(MyDataInfo.Dog.newBuilder().
                            setName("dog").
                            setAge(21).
                            build()).
                    build();
        } else {
            myMessage = MyDataInfo.MyMessage.newBuilder().
                    setDataType(MyDataInfo.MyMessage.DataType.CatType).
                    setCat(MyDataInfo.Cat.newBuilder().
                            setName("cat").
                            setCity("shanghai").
                            build()).
                    build();
        }

        ctx.channel().writeAndFlush(myMessage);
    }
}