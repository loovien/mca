package rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.io.IOException;

public class MQConsumerTest {

    public static void main(String[] args) throws MQClientException, IOException {
        DefaultMQPushConsumer defaultMQPushConsumer = new DefaultMQPushConsumer("demo-csm");
        defaultMQPushConsumer.setNamesrvAddr("192.168.163.184:9876");
        defaultMQPushConsumer.subscribe("TopicTest", "tagA");

        defaultMQPushConsumer.registerMessageListener((MessageListenerConcurrently) (list, consumeConcurrentlyContext) -> {
            for (MessageExt messageExt : list) {
                System.out.println("messageExt = " + messageExt);
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        defaultMQPushConsumer.start();

        System.in.read();
    }
}
