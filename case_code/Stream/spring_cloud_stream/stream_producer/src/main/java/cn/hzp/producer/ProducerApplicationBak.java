package cn.hzp.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

/**
 * 入门案例:
 *      1.引入依赖
 *      2.配置application.yml文件
 *      3.发送消息的话,定义一个通道接口,通过接口中内置的messagechannel
 *              springcloudstream中内置接口  Source
 *      4.@EnableBinding : 绑定对应通道
 *      5.发送消息的话,通过MessageChannel发送消息
 *          * 如果需要MessageChannel --> 通过绑定的内置接口获取
 */
//优化前代码关闭 @SpringBootApplication
//@EnableBinding(Source.class)//使用一个输出通道的可绑定接口
public class ProducerApplicationBak implements CommandLineRunner {

	@Autowired
	MessageChannel output;

	@Override
	public void run(String... args) throws Exception {
		//发送MQ消息
		//MessageBuilder  工具类：创建消息
		output.send(MessageBuilder.withPayload("hello huangzp").build());
	}

	public static void main(String[] args) {
		SpringApplication.run(ProducerApplicationBak.class);
	}
}
