package de.philipphauer.articleservice.db;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ArticleMongoDBTest {

    private ArticleRepository repo;

    @Before
    public void clean() {
        repo = RepositoryFactory.createArticleRepository();
        repo.deleteAll();
    }

    @Test
    public void save() {
        repo.save(new ArticleBean("Feuerwehr", "Lego"));
        assertThat(repo.count()).isEqualTo(1);
    }

    @Test
    public void find() {
        ArticleBean legoFeuerwehr = new ArticleBean("Feuerwehr", "Lego");
        ArticleBean legoTodesstern = new ArticleBean("Todesstern", "Lego");
        ArticleBean playmobileFeuerwehr = new ArticleBean("Feuerwehr", "Playmobile");
        ArrayList<ArticleBean> articles = Lists.newArrayList(legoFeuerwehr, legoTodesstern, playmobileFeuerwehr);
        repo.save(articles);

        List<ArticleBean> freshArticles = repo.findByCategory("Lego");
        assertThat(freshArticles).hasSize(2)
                .contains(legoFeuerwehr, legoTodesstern)
                .doesNotContain(playmobileFeuerwehr);
    }
}
