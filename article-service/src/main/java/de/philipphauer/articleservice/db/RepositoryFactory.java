package de.philipphauer.articleservice.db;

import com.mongodb.MongoClient;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import java.net.UnknownHostException;

public class RepositoryFactory {

    public static ArticleRepository createArticleRepository(){
        try {
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(mongoClient, "test");
            MongoTemplate mongoOptions = new MongoTemplate(mongoDbFactory);
            RepositoryFactorySupport factory = new MongoRepositoryFactory(mongoOptions);
            return factory.getRepository(ArticleRepository.class);
        } catch (UnknownHostException e) {
            throw new RuntimeException("Couldn't create ArticleRepository",e);
        }
    }
}
