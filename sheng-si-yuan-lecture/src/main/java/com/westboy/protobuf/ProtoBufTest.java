package com.westboy.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * ProtoBuf 测试类
 *
 * @author westboy
 * @date 2018-12-11
 * @since 1.0
 */
public class ProtoBufTest {
    public static void main(String[] args) throws InvalidProtocolBufferException {

        DataInfo.Student student1 = DataInfo.Student.newBuilder().setName("张三").setAge(20).setAddress("北京").build();

        byte[] student2ByteArray = student1.toByteArray();

        DataInfo.Student student2 = DataInfo.Student.parseFrom(student2ByteArray);

        System.out.println(student2);
        System.out.println(student2.getName());
        System.out.println(student2.getAge());
        System.out.println(student2.getAddress());
    }
}
