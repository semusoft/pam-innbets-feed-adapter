package com.betting.feed.adapter.kafka;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.betting.feed.adapter.kafka.model.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.time.Instant;

@Slf4j
@Service
public class Producer {
    @Value("${feed.kafka-server.topic.name}")
    private String topic;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final XmlMapper xmlMapper;
    private final ObjectMapper jsonMapper;
    private static final SecureRandom random = new SecureRandom();

    public Producer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.xmlMapper = new XmlMapper();
        this.jsonMapper = new ObjectMapper();
    }

    public static String generateTransactionId() {
        long currentTimeMillis = System.currentTimeMillis();
        int randomNum = random.nextInt(1_000_000);  // 6-digit random number
        return String.format("%d-%06d", currentTimeMillis, randomNum);
    }

    public void sendTransaction(byte[] xmlMessage, String action, boolean isLive) {
        try {
            JsonNode jsonNode = xmlMapper.readTree(xmlMessage);
            Transaction transaction = Transaction.builder()
                    .transactionId(generateTransactionId())
                    .timestamp(Timestamp.from(Instant.now()))
                    .action(action)
                    .info(jsonNode)
                    .isLive(isLive)
                    .build();
            String transactionString = jsonMapper.writeValueAsString(transaction);
            ProducerRecord<String, String> record =
                    new ProducerRecord<>(topic, transaction.getTransactionId(), transactionString);
            kafkaTemplate.send(record).whenComplete((result, exception) -> {
                if (exception == null) {
                    log.debug("✅ Transaction sent: {}", transaction.getTransactionId());
                } else {
                    log.error("❌ Error sending transaction", exception);
                }
            });
        } catch (Exception e) {
            log.error("❌ Error during XML-to-JSON serialization", e);
        }
    }
}