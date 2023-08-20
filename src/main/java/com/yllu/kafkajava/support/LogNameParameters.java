package com.yllu.kafkajava.support;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

@Slf4j
public class LogNameParameters {
    public static final String TOPIC = "TOPIC";
    public static final String KEY = "KEY";
    public static final String MESSAGE = "MESSAGE";
    public static final String PARTITION = "PARTITION";
    public static final String OFFSET = "OFFSET ";
    public static final String TIMESTAMP = "TIMESTAMP";


    public static void logKafkaRecordMetadata(RecordMetadata metadata, String key, String value) {
        log.info("Received metadata - \n[\n{}:{}\n{}:{}\n{}:{}\n{}:{}\n{}:{}\n{}:{}\n]",
                KEY, key,
                MESSAGE, value,
                TOPIC, metadata.topic(),
                PARTITION, metadata.partition(),
                OFFSET, metadata.offset(),
                TIMESTAMP, metadata.timestamp());
    }

    public static void logKafkaConsumerRecord(ConsumerRecord consumerRecord) {
        log.info("Consumer record - Data has been received - \n[\n{}:{}\n{}:{}\n{}:{}\n{}:{}\n{}:{}\n{}:{}\n]",
                KEY, consumerRecord.key(),
                MESSAGE, consumerRecord.value(),
                TOPIC, consumerRecord.topic(),
                PARTITION, consumerRecord.partition(),
                OFFSET, consumerRecord.offset(),
                TIMESTAMP, consumerRecord.timestamp());
    }

}
