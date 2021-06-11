package com.craftingdatascience.bbl.etl.infrastructure.config;

import com.craftingdatascience.bbl.etl.infrastructure.mongo.MongoProperties;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Configuration
public class MongoConfiguration {
    @Bean
    public MongoClient mongoClient(MongoProperties mongoProperties) {
        String uri = mongoProperties.getUri();
        MongoClientURI mongoClientURI = new MongoClientURI(uri, MongoClientOptions.builder().codecRegistry(pojoCodecRegistry()));

        return new MongoClient(mongoClientURI);
    }

    @Bean
    public CodecRegistry pojoCodecRegistry() {
        return fromRegistries(
                MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build())
        );
    }

    @Bean
    @ConfigurationProperties("mongo")
    public MongoProperties mongoProperties() {
        return new MongoProperties();
    }
}
