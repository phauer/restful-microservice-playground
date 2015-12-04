package de.philipphauer.articleservice;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import de.philipphauer.articleservice.db.ArticleBean;
import de.philipphauer.articleservice.db.ArticleRepository;
import de.philipphauer.articleservice.db.RepositoryFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/articles")
@Produces(MediaType.APPLICATION_JSON)
public class ArticleResource {

    private final ArticleRepository repo;

    public ArticleResource() {
        repo = RepositoryFactory.createArticleRepository();
    }

    @GET
    @Timed
    public List<ArticleResponse> getArticles(@QueryParam("name") Optional<String> name) {
        if (name.isPresent()) {
            ArticleBean article = repo.findByName(name.get());
            if (article == null) {
                return Lists.newArrayList();
            }
            return Lists.newArrayList(mapToResponse(article));
        }
        List<ArticleBean> articles = repo.findAll();
        return mapToResponse(articles);
    }

    private List<ArticleResponse> mapToResponse(List<ArticleBean> articles) {
        return articles.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private ArticleResponse mapToResponse(ArticleBean article) {
        return new ArticleResponse(article.getName());
    }

}