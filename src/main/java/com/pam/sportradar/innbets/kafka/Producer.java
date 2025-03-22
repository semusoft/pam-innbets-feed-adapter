package com.pam.sportradar.innbets.kafka;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.pam.sportradar.innbets.commons.PriorityType;
import com.pam.sportradar.innbets.kafka.model.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Slf4j
@Service
public class Producer {
    @Value("${feed.kafka-server.high-priority-topic.name}")
    private String highPriorityTopicNAme;
    @Value("${feed.kafka-server.low-priority-topic.name}")
    private String lowPriorityTopicNAme;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final XmlMapper xmlMapper;
    private final ObjectMapper jsonMapper;

    public Producer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.xmlMapper = new XmlMapper();
        this.jsonMapper = new ObjectMapper();
    }

    public void sendTransaction(byte[] xmlMessage, String action, String priority) {
        try {
            JsonNode jsonNode = xmlMapper.readTree(xmlMessage);
            Transaction transaction = Transaction.builder()
                    .transactionId(UUID.randomUUID().toString())
                    .timestamp(Timestamp.from(Instant.now()))
                    .action(action)
                    .info(jsonNode)
                    .priority(priority)
                    .build();
            String transactionString = jsonMapper.writeValueAsString(transaction);
            String topic = priority.equalsIgnoreCase(PriorityType.HIGH.getValue()) ? highPriorityTopicNAme : lowPriorityTopicNAme;
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