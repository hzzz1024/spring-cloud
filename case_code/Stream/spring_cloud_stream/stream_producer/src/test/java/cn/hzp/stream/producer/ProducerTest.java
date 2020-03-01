package cn.hzp.stream.producer;

import cn.hzp.producer.ProducerApplication;
import cn.hzp.producer.sender.MessageSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProducerApplication.class)
public class ProducerTest {

    @Autowired
    private MessageSender messageSender;

    @Test
    public void testSend() {
        //发布消息
        messageSender.send();
    }
    @Test
    public void testPartitionSend() {
        //测试消息分区
        for (int i=0;i<5;i++) {
            messageSender.send("1");
        }
    }
}
