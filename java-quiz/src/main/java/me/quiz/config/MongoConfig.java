package me.quiz.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories("me.quiz.mongo.repo")
@ComponentScan("me.quiz.mongo.repo")
public class MongoConfig extends AbstractMongoConfiguration {

    @Value("${mongo.host:quiz-mongodb}")
    public String host;

    @Value("${mongo.db:quest}")
    public String db;

    @Override
    protected String getDatabaseName() {
        return db;
    }

    @Bean
    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient(
                new ServerAddress(host, 27017)
        );
    }
}
