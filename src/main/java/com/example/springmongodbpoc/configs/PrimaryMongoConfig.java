package com.example.springmongodbpoc.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.example.springmongodbpoc.model.repos.primary",
        mongoTemplateRef = "primaryMongoTemplate")
public class PrimaryMongoConfig {

}
