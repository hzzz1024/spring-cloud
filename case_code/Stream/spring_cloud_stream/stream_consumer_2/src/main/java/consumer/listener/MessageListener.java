package consumer.listener;

import consumer.channel.MyBindind;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

/**
 * 1.引入依赖
 * 2.配置application.yml
 * 3.需要配置一个通道的接口
 *    内置获取消息的通道接口 sink
 * 4.绑定通道
 * 5.配置一个监听方法 : 当程序从中间件获取数据之后,执行的业务逻辑方法
 *      需要在监听方法上配置@StreamListener
 */

@Component
@EnableBinding(MyBindind.class)
public class MessageListener {

	//监听binding中的消息
	@StreamListener(MyBindind.MY_INPUT)
	public void input(String message) {
		System.out.println("获取到消息: "+message);
	}

}
