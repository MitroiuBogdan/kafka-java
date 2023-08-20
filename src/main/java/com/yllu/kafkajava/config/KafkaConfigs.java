package com.yllu.kafkajava.config;

import com.yllu.kafkajava.support.ConsumerGroupStrategy;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaConfigs {


    public static Properties setupProperties() {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "cluster.playground.cdkt.io:9092");
        properties.setProperty("security.protocol", "SASL_SSL");
        properties.setProperty("sasl.jaas.config", "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"50mosLQq6nxUMwl9lmI8sz\" password=\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL2F1dGguY29uZHVrdG9yLmlvIiwic291cmNlQXBwbGljYXRpb24iOiJhZG1pbiIsInVzZXJNYWlsIjpudWxsLCJwYXlsb2FkIjp7InZhbGlkRm9yVXNlcm5hbWUiOiI1MG1vc0xRcTZueFVNd2w5bG1JOHN6Iiwib3JnYW5pemF0aW9uSWQiOjc1NDc1LCJ1c2VySWQiOjg3ODExLCJmb3JFeHBpcmF0aW9uQ2hlY2siOiJhYzA1MDMwOC0yM2RjLTQ4YjItYmI2Yy0zZDI0YzA1Nzk5NDUifX0.WohtXp_cihrKvS8HCXsGgnyv6U3viI6S2A9tckZxSC8\";");
        properties.setProperty("sasl.mechanism", "PLAIN");

        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", StringSerializer.class.getName());


        properties.setProperty("key.deserializer", StringDeserializer.class.getName());
        properties.setProperty("value.deserializer", StringDeserializer.class.getName());

        properties.setProperty("group.id", ConsumerGroupStrategy.CONSUMER_GROUP_1);
        properties.setProperty("auto.offset.reset", ConsumerGroupStrategy.earliest);
        return properties;
    }
}
