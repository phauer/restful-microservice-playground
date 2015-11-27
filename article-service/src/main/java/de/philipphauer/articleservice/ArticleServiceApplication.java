package de.philipphauer.articleservice;

import com.mongodb.Mongo;
import de.philipphauer.articleservice.db.ArticleBean;
import de.philipphauer.articleservice.db.ArticleRepository;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import java.net.UnknownHostException;

/**
 * Created by hauer on 27.11.2015.
 */
public class ArticleServiceApplication extends Application<ArticleServiceConfiguration> {
    public static void main(String[] args) throws Exception {
        new ArticleServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<ArticleServiceConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(ArticleServiceConfiguration configuration,
                    Environment environment) {
        ArticleResource resource = new ArticleResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        environment.jersey().register(resource);

        try {
            Mongo mongo = new Mongo( "localhost", 27017 );
            MongoTemplate mongoOptions = new MongoTemplate(mongo, "articles");
            RepositoryFactorySupport factory = new MongoRepositoryFactory(mongoOptions);
            ArticleRepository repo = factory.getRepository(ArticleRepository.class);
            repo.save(new ArticleBean("Auto"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

}