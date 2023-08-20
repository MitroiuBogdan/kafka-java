package com.yllu.kafkajava;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Component;

import static com.yllu.kafkajava.config.KafkaConfigs.setupProperties;
import static com.yllu.kafkajava.support.LogNameParameters.TOPIC;
import static com.yllu.kafkajava.support.LogNameParameters.logKafkaRecordMetadata;

@Component
@Slf4j
public class Producer {

    private KafkaProducer<String, String> kafkaProducer;


    public Producer() {
        kafkaProducer = new KafkaProducer<>(setupProperties());
    }


    public void send(String topic, String value) {
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, value);
        kafkaProducer.send(producerRecord);

        kafkaProducer.flush(); //send data and block until done

        kafkaProducer.close();
    }

    public void sendWithCallback(String topic, String value) {
        sendWithCallback(topic, null, value);

    }


    public void sendWithCallback(String topic, String key, String value) {
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, key, value);

        kafkaProducer.send(producerRecord, ((metadata, exception) -> {
            if (exception == null) {
                logKafkaRecordMetadata(metadata, key, value);
            } else {
                log.error("An error has occurred while trying to send message on {}:{}", TOPIC, metadata.topic(), exception);
            }

        }));
    }

    public void closeProducer() {
        kafkaProducer.flush(); //send data and block until done
        kafkaProducer.close();
    }


}
