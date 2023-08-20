package com.yllu.kafkajava;

import com.yllu.kafkajava.config.KafkaConfigs;
import com.yllu.kafkajava.support.LogNameParameters;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Arrays;


@Slf4j
@Component
public class Consumer {


    private KafkaConsumer<String, String> kafkaConsumer;

    public Consumer() {
        kafkaConsumer = new KafkaConsumer<>(KafkaConfigs.setupProperties());
    }


    public void poll(String[] topics) {

        kafkaConsumer.subscribe(Arrays.asList(topics));

        while (true) {
            log.info("Polling");
            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(Duration.ofMillis(1000));

            for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                LogNameParameters.logKafkaConsumerRecord(consumerRecord);
            }
        }
    }
}
