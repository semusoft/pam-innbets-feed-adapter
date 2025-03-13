package com.pam.sportradar.innbets.kafka;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Service
public class Producer {
    private final String topic = "st-feed-topic";
    private final KafkaTemplate<String, String> kafkaTemplate;

    public Producer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendTransaction(String message, String action) {
        Timestamp now = Timestamp.from(Instant.now());
        String id = UUID.randomUUID().toString();
        Transaction transaction = Transaction.builder()
                .transactionId(id)
                .timestamp(now)
                .action(action)
                .info(message)
                .build();
        String transactionString = transaction.toString();
        ProducerRecord<String, String> record =
                new ProducerRecord<>(topic, transaction.getTransactionId(), transactionString);
        kafkaTemplate.send(record).whenComplete((result, exception) -> {
            if (exception == null) {
                System.out.println("✅ Transaction sent: " + transaction.getTransactionId());
            } else {
                System.err.println("❌ Error sending transaction: " + exception.getMessage());
            }
        });
    }
}

