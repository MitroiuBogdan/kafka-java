package com.yllu.kafkajava;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class KafkaJavaApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(KafkaJavaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Hello form the log");
    }
}
