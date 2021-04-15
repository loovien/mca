package rocketmq;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.Scanner;

public class MQSenderTest {
    public static void main(String[] args) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer("mq-demo");
        defaultMQProducer.setSendMsgTimeout(10000);


        defaultMQProducer.setNamesrvAddr("192.168.163.184:9876");
        // defaultMQProducer.setNamesrvAddr("localhost:9876");

        defaultMQProducer.start();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String next = scanner.next();
            if (next.equals("exit")) {
                break;
            }
            System.out.println("send to rocketmq: " + next);
            SendResult topicTest = defaultMQProducer.send(new Message("TopicTest", next.getBytes()));
            System.out.println(topicTest);
        }

    }
}
