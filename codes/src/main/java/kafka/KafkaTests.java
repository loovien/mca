package kafka;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.*;

public class KafkaTests {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "");
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

        producer.beginTransaction();
        ProducerRecord<String, String> record = new ProducerRecord<String, String>("topic-luowen", "hello", "luowen");
        producer.send(record);
        producer.commitTransaction();
    }

    public void consumer() {
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "luowen");
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

        ArrayList<String> topics = new ArrayList<>();
        topics.add("xx");
        consumer.subscribe(topics, new ConsumerRebalanceListener() {
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> collection) {
                System.out.println(collection);
            }

            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> collection) {
                System.out.println(collection);
            }
        });

        Set<TopicPartition> partitions = consumer.assignment();

        if (partitions.size() <= 0) {
            consumer.poll(Duration.ofSeconds(2));
            partitions = consumer.assignment();
        }

        for (TopicPartition partition : partitions) {
            // consumer.seek(partition, 0);
        }
        ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(10));
        for (ConsumerRecord<String, String> record : records) {
            System.out.println(record);
        }

        Set<TopicPartition> partitions1 = records.partitions();
        for (TopicPartition topicPartition : partitions1) {
            List<ConsumerRecord<String, String>> pRecord = records.records(topicPartition);
            // current partition last offset
            long offset = pRecord.get(pRecord.size() - 1).offset();

            LinkedHashMap<TopicPartition, OffsetAndMetadata> map = new LinkedHashMap<>();
            map.put(topicPartition, new OffsetAndMetadata(offset));
            Iterable<ConsumerRecord<String, String>> records1 = records.records(topicPartition.topic());
            consumer.commitSync(map);
        }

        consumer.commitAsync();
        consumer.close();
    }
}
