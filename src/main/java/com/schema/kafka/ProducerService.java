package com.schema.kafka;

import java.nio.charset.StandardCharsets;

import org.apache.kafka.common.header.internals.RecordHeader;
import org.apache.kafka.common.header.internals.RecordHeaders;

import org.eclipse.microprofile.reactive.messaging.Outgoing;

import io.smallrye.reactive.messaging.kafka.KafkaRecord;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class ProducerService {
  @Outgoing("my-kafka-producer")
    public KafkaRecord<String, String> sendMessage() {
        String message = "Hello, Kafka!";
        RecordHeaders headers = new RecordHeaders();
        headers.add(new RecordHeader("message-type", "greeting".getBytes(StandardCharsets.UTF_8)));

        return KafkaRecord.of("my-kafka-topic", null, message, null, 0);

    }

}
