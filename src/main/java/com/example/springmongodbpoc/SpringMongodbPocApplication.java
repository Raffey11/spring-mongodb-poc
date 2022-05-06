package com.example.springmongodbpoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication(exclude = {MongoAutoConfiguration.class})
public class SpringMongodbPocApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMongodbPocApplication.class, args);
    }

}
