package com.example.kakfademo;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class StockConsumer {

    private static final String TOPIC = "stock-topic";

    @KafkaListener(topics = TOPIC, groupId = "stock-group")
    public void consume(ConsumerRecord<String, String> record) {
        System.out.println("Received: Key=" + record.key() + ", Value=" + record.value() +
                ", Partition=" + record.partition() + ", Offset=" + record.offset());
    }

}
