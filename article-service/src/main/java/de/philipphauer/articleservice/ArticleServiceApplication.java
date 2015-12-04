package de.philipphauer.articleservice;

import com.google.common.collect.Lists;
import de.philipphauer.articleservice.db.ArticleBean;
import de.philipphauer.articleservice.db.ArticleRepository;
import de.philipphauer.articleservice.db.RepositoryFactory;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

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
        ArticleRepository repo = RepositoryFactory.createArticleRepository();
        repo.deleteAll();
        repo.save(Lists.newArrayList(new ArticleBean("Feuerwehr", "Lego"), new ArticleBean("Todesstern", "Lego")));
    }

    @Override
    public void run(ArticleServiceConfiguration configuration,
                    Environment environment) {
        ArticleResource resource = new ArticleResource();
        environment.jersey().register(resource);
    }

}