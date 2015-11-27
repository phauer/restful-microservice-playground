package de.philipphauer.articleservice;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/articles")
@Produces(MediaType.APPLICATION_JSON)
public class ArticleResource {
    private final String template;
    private final String defaultName;

    public ArticleResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
    }

    @GET
    @Timed
    public List<Article> getArticles(@QueryParam("name") Optional<String> name) {
        final String value = String.format(template, name.or(defaultName));
        return Lists.newArrayList(new Article("Auto"), new Article("Lego"), new Article("Puppe"));
    }
}