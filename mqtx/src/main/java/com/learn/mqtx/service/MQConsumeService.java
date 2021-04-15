package com.learn.mqtx.service;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class MQConsumeService {

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    protected void sender() throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        System.out.println("=============================send==============");
        DefaultMQProducer xxo = new DefaultMQProducer("xxo");
        xxo.setNamesrvAddr("192.168.163.184:9876");
        xxo.start();
        while (true) {
            TimeUnit.SECONDS.sleep(1);
            Message xxo2 = new Message("xxo", "from idea".getBytes());
            SendResult xxo1 = xxo.send(xxo2);
            System.out.println("sendResult: " + xxo1);
        }

    }

    public void msgListener() {
        System.out.println("======================= listener ");
        System.out.println(Thread.currentThread().getName() + " starting");
        DefaultMQPushConsumer defaultMQPushConsumer = new DefaultMQPushConsumer("mq-demo-maomao");
//        defaultMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        defaultMQPushConsumer.setNamesrvAddr("192.168.163.184:9876");
//        defaultMQPushConsumer.setMessageModel(MessageModel.BROADCASTING);
        try {
            defaultMQPushConsumer.subscribe("xxoo", "*");
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        defaultMQPushConsumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            for (MessageExt msg : msgs) {
                System.out.println(Thread.currentThread().getName() + " ==========msg: " + msg.getMsgId() + " name; " + new String(msg.getBody()));
            }
            if (atomicInteger.incrementAndGet() > 4) {
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
            System.out.println("=========== consume later ");
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        });
        try {
            defaultMQPushConsumer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }

    }
}
