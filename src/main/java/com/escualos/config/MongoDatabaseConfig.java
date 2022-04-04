package com.escualos.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@EnableReactiveMongoRepositories(
        basePackages = "com.escualos.repository",
        basePackageClasses = {MongoDatabaseConfig.class}
)
public class MongoDatabaseConfig extends AbstractReactiveMongoConfiguration {

    @Value("${escuagest.uri}")
    String uri;

    @Value("${escuagest.database-name}")
    String databaseName;

    @Override
    protected String getDatabaseName() {
        return databaseName;
    }

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(uri);
    }
}
