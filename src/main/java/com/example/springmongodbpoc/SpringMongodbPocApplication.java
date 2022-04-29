package com.example.springmongodbpoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
@EnableFeignClients
public class SpringMongodbPocApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMongodbPocApplication.class, args);
    }

}
