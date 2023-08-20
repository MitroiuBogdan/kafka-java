package com.yllu.kafkajava;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Component;

import java.util.Properties;

import static com.yllu.kafkajava.LogNameParameters.*;

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
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, value);

        kafkaProducer.send(producerRecord, ((metadata, exception) -> {
            if (exception == null) {
                logKafkaRecordMetadata(metadata);
            } else {
                log.error("An error has occurred while trying to send message on {}:{}", TOPIC, metadata.topic(), exception);
            }

        }));

        kafkaProducer.flush(); //send data and block until done
        kafkaProducer.close();

    }


    private Properties setupProperties() {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "cluster.playground.cdkt.io:9092");
        properties.setProperty("security.protocol", "SASL_SSL");
        properties.setProperty("sasl.jaas.config", "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"50mosLQq6nxUMwl9lmI8sz\" password=\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL2F1dGguY29uZHVrdG9yLmlvIiwic291cmNlQXBwbGljYXRpb24iOiJhZG1pbiIsInVzZXJNYWlsIjpudWxsLCJwYXlsb2FkIjp7InZhbGlkRm9yVXNlcm5hbWUiOiI1MG1vc0xRcTZueFVNd2w5bG1JOHN6Iiwib3JnYW5pemF0aW9uSWQiOjc1NDc1LCJ1c2VySWQiOjg3ODExLCJmb3JFeHBpcmF0aW9uQ2hlY2siOiJhYzA1MDMwOC0yM2RjLTQ4YjItYmI2Yy0zZDI0YzA1Nzk5NDUifX0.WohtXp_cihrKvS8HCXsGgnyv6U3viI6S2A9tckZxSC8\";");
        properties.setProperty("sasl.mechanism", "PLAIN");

        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", StringSerializer.class.getName());

        return properties;
    }
}
