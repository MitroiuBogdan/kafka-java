package com.yllu.kafkajava;


import com.yllu.kafkajava.support.Topics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MainFlowService {

    private final Producer producer;
    private final Consumer consumer;


    public MainFlowService(Producer producer, Consumer consumer) {
        this.producer = producer;
        this.consumer = consumer;
    }

    public void start() {
//        startProducing();
        startConsuming();
    }

    public void startProducing() {
        for (int i = 0; i < 10; i++) {
            producer.sendWithCallback(Topics.TOPIC_1, "user_1", "Message " + i);
        }
        producer.closeProducer();
    }

    public void startConsuming() {
        String[] topics = {Topics.TOPIC_1};
        consumer.poll(topics);
    }
}
