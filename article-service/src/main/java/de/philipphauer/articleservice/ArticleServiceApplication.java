package de.philipphauer.articleservice;

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
    }

}