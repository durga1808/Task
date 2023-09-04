package com.schema.kafka;

import org.eclipse.microprofile.reactive.messaging.Incoming;

public class KafkaConsumer {
    
    @Incoming("my-kafka-producer")
    public void consumeMessage(String message) {
        System.out.println("Kafka message " + message);
    }
}
