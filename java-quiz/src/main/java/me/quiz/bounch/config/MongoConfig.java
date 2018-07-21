package me.quiz.bounch.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories("me.quiz.bounch.mongo.repo")
@ComponentScan("me.quiz.bounch.mongo.repo")
public class MongoConfig extends AbstractMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "bounch";
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient(
                new ServerAddress("localhost", 27017)
        );
    }
}
