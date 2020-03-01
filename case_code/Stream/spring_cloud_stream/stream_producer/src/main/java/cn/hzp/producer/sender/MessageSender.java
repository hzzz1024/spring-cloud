package cn.hzp.producer.sender;

import cn.hzp.producer.channel.MyBindind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * 入门案例:
 * 1.引入依赖
 * 2.配置application.yml文件
 * 3.发送消息的话,定义一个通道接口,通过接口中内置的messagechannel
 * springcloudstream中内置接口  Source
 * 4.@EnableBinding : 绑定对应通道
 * 5.发送消息的话,通过MessageChannel发送消息
 * * 如果需要MessageChannel --> 通过绑定的内置接口获取
 */

/**
 * 负责向中间件发送数据
 */
@Component
@EnableBinding(MyBindind.class)//使用一个输出通道的可绑定接口
public class MessageSender {

    @Autowired
    @Qualifier(value=MyBindind.MY_OUTPUT) //注入名称为myOutput的实现了MessageChannel接口的实例
    MessageChannel output;

    //发送消息
    public void send() {
        //发送MQ消息
        //MessageBuilder  工具类：创建消息
        output.send(MessageBuilder.withPayload("hello huangzp").build());
    }

    //发送消息
    public void send(Object obj) {
        output.send(MessageBuilder.withPayload(obj).build());
    }
}
