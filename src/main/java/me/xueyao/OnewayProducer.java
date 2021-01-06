package me.xueyao;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * 单向模式
 * @author Simon.Xue
 * @date 1/6/21 5:21 PM
 **/
public class OnewayProducer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("onewayProducer1");

        producer.setNamesrvAddr("localhost:9876");

        producer.start();

        for (int i = 0; i < 10; i++) {
            Message msg = new Message("TopicTest",
                    "TagA",
                    ("Hello RocketMQ" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));

            producer.sendOneway(msg);
        }


        Thread.sleep(5000);
        producer.shutdown();
    }
}
