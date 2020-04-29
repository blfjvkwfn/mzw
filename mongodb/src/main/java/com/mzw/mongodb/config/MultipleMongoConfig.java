package com.mzw.mongodb.config;

import com.mzw.mongodb.config.props.MultipleMongoProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * @author Jonathan Meng
 * @date 16/05/2019
 */
@Configuration
public class MultipleMongoConfig {
    @Autowired
    private MultipleMongoProperties mongoProperties;

    @Primary
    @Bean(name = "primaryMongoTemplate")
    public MongoTemplate primaryMongoTemplate() {
        return new MongoTemplate(primaryFactory(mongoProperties.getPrimary()))
    }

    @Bean
    @Primary
    private MongoDbFactory primaryFactory(MongoProperties primary) {
        primary.get
    }

}
