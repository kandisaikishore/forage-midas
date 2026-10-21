package com.jpmc.midascore;

import com.jpmc.midascore.foundation.Transaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import java.util.concurrent.CompletableFuture;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {
    private final String topic;
    private final KafkaTemplate<String, Transaction> kafkaTemplate;

    public KafkaProducer(@Value("${general.kafka-topic}") String topic, KafkaTemplate<String, Transaction> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String transactionLine) {
        if (transactionLine == null || transactionLine.isBlank()) {
            throw new IllegalArgumentException("Transaction line must not be blank");
        }

        String[] transactionData = transactionLine.split(",\\s*");
        if (transactionData.length != 3) {
            throw new IllegalArgumentException("Transaction line must contain senderId, recipientId and amount");
        }

        try {
            long senderId = Long.parseLong(transactionData[0].trim());
            long recipientId = Long.parseLong(transactionData[1].trim());
            float amount = Float.parseFloat(transactionData[2].trim());
            if (!Float.isFinite(amount) || amount <= 0) {
                throw new IllegalArgumentException("Transaction amount must be a finite positive number");
            }
            CompletableFuture<SendResult<String, Transaction>> sendResult =
                    kafkaTemplate.send(topic, new Transaction(senderId, recipientId, amount));
            sendResult.whenComplete((result, exception) -> {
                if (exception != null) {
                    org.slf4j.LoggerFactory.getLogger(KafkaProducer.class)
                            .error("Failed to publish transaction to topic {}", topic, exception);
                }
            });
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("Transaction line contains an invalid numeric value", exception);
        }
    }
}
