package com.yllu.kafkajava;

import com.yllu.kafkajava.config.KafkaConfigs;
import com.yllu.kafkajava.support.LogNameParameters;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Arrays;


@Slf4j
@Component
public class Consumer {


    private KafkaConsumer<String, String> kafkaConsumer;

    public Consumer() {
        kafkaConsumer = new KafkaConsumer<>(KafkaConfigs.setupProperties());
        shutDownConsumerOnExit();
    }


    public void poll(String[] topics) {

        try {
            kafkaConsumer.subscribe(Arrays.asList(topics));
            while (true) {
                log.info("Polling");
                ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(Duration.ofMillis(1000));

                for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                    LogNameParameters.logKafkaConsumerRecord(consumerRecord);
                }
            }
        } catch (WakeupException wakeupException) {
            log.error("Shutting down the consumer due to wakeup exception", wakeupException);
        } finally {
            kafkaConsumer.close();
            log.info("The consumer is gracefully shut down");
        }
    }

    private void shutDownConsumerOnExit() {
        final Thread currentThread = Thread.currentThread();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            kafkaConsumer.wakeup();
            try {
                currentThread.join();
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
        }));
    }
}
