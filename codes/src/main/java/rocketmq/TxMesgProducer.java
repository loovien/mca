package rocketmq;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.selector.SelectMessageQueueByHash;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.Executors;

public class TxMesgProducer {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        TransactionMQProducer transactionMQProducer = new TransactionMQProducer("cluster-a");
//        transactionMQProducer.setExecutorService(Executors.newSingleThreadExecutor());
        transactionMQProducer.setNamesrvAddr("localhost:9876");
        transactionMQProducer.setTransactionListener(new TransactionListener() {
            @Override
            public LocalTransactionState executeLocalTransaction(Message message, Object o) {
                System.out.println("\"transaction commit: \" + message = " + "transaction commit: " + message);
                if (new String(message.getBody()).contains("luowen")) {
                    return LocalTransactionState.COMMIT_MESSAGE;
                }
                return LocalTransactionState.ROLLBACK_MESSAGE;
            }

            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
                System.out.println("rocketmq callback checking: " + messageExt);
                return LocalTransactionState.COMMIT_MESSAGE;
            }
        });
        transactionMQProducer.start();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String next = scanner.next();
            if (next.equals("exit")) {
                break;
            }
            Message msg = new Message("topic-room", next.getBytes(StandardCharsets.UTF_8));
            SendResult send = transactionMQProducer.sendMessageInTransaction(msg, next);
            System.out.println(send);
        }
        transactionMQProducer.shutdown();
        System.out.println("stoped");

    }
}
