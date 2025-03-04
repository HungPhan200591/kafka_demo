package com.example.kakfademo;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StockProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "stock-topic";

    private final Map<String, Double> stocks = Map.of(
            "AAPL", 10000.0,
            "GOOGL", 20000.0,
            "AMZN", 30000.0
    );

    @Scheduled(fixedRate = 10000)
    private void sendStockPrice() {

        stocks.forEach((symbol, price) -> {
            double updatedPrice = price + getStockPriceOffset();
            kafkaTemplate.send(new ProducerRecord<>(TOPIC, symbol, String.valueOf(updatedPrice)));
            System.out.println("Sent: " + symbol + " -> " + updatedPrice);
        });
    }

    private Double getStockPriceOffset() {
        LocalTime now = LocalTime.now();
        int minutes = now.getMinute();
        int seconds = now.getSecond();
        double decimalSeconds = seconds / 100.0;
        return minutes + decimalSeconds;
    }
}
