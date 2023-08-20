package com.yllu.kafkajava;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;

@Slf4j
public class LogNameParameters {
    public static final String TOPIC = "TOPIC";
    public static final String MESSAGE = "MESSAGE";
    public static final String PARTITION = "PARTITION";
    public static final String OFFSET = "OFFSET ";
    public static final String TIMESTAMP = "TIMESTAMP";


    public static void logKafkaRecordMetadata(RecordMetadata metadata) {
        log.info("Received metadata - \n[\n{}:{}\n{}:{}\n{}:{}\n{}:{}\n]",
                TOPIC, metadata.topic(),
                PARTITION, metadata.partition(),
                OFFSET, metadata.offset(),
                TIMESTAMP, metadata.timestamp());
    }

}
