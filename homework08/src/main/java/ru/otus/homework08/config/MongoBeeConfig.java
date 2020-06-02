package ru.otus.homework08.config;

import com.github.mongobee.Mongobee;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.homework08.changelog.DatabaseChangelog;

@Configuration
public class MongoBeeConfig {

    private final MongoTemplate mongoTemplate;
    private final MongoClient mongo;
    private final String DB_NAME = "homework08";

    public MongoBeeConfig(MongoClient mongo, MongoTemplate mongoTemplate) {
        this.mongo = mongo;
        this.mongoTemplate = mongoTemplate;
    }

    @Bean
    public Mongobee mongobee(Environment environment) {
        Mongobee runner = new Mongobee(mongo);
        runner.setDbName(DB_NAME);
        runner.setChangeLogsScanPackage(DatabaseChangelog.class.getPackage().getName());
        runner.setSpringEnvironment(environment);
        runner.setMongoTemplate(mongoTemplate);
        return runner;
    }

}
