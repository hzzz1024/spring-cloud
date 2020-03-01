package cn.hzp.producer.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * 自定义的消息通道
 * 可以定义多个binding
 */
public interface MyBindind {

	/**
	 * 消息生产者的配置
	 */
	String MY_OUTPUT = "myOutput";

	//容器中会生成一个名称为myOutput的实现了SubscribableChannel接口的实例对象
	@Output(MY_OUTPUT)
    MessageChannel myOutput();

	/**
	 * 消息消费者的配置
	 */
	String MY_INPUT = "myInput";

	//容器中会生成一个名称为myInput的实现了SubscribableChannel接口的实例对象
	@Input(MY_INPUT)
    SubscribableChannel myInput();
}
