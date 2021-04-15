package rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.io.IOException;

public class TxConsumer {
    public static void main(String[] args) throws MQClientException, IOException {
        DefaultMQPushConsumer defaultMQPushConsumer = new DefaultMQPushConsumer("cluster-b");
        defaultMQPushConsumer.setNamesrvAddr("localhost:9876");
        defaultMQPushConsumer.subscribe("topic-room", "*");
        defaultMQPushConsumer.setMessageListener((MessageListenerOrderly) (list, consumeOrderlyContext) -> {
            System.out.println("total size: " + list.size());
            for (MessageExt messageExt : list) {
                System.out.println("messageExt = " + new String(messageExt.getBody()));
                System.out.println("messageExt txId= " + messageExt.getTransactionId());
            }
            System.out.println("===========ending================");
            return ConsumeOrderlyStatus.SUCCESS;
        });
        defaultMQPushConsumer.start();
        while (true) {
            int read = System.in.read();
            if (read >= 97) {
                defaultMQPushConsumer.shutdown();
                break;
            }
        }
        System.out.println("transaction comsumer stoped!!!");
    }
}
