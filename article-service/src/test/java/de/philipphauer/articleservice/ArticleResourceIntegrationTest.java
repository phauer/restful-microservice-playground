package de.philipphauer.articleservice;

import de.philipphauer.articleservice.db.ArticleBean;
import de.philipphauer.articleservice.db.ArticleRepository;
import de.philipphauer.articleservice.db.RepositoryFactory;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.ClassRule;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ArticleResourceIntegrationTest {

    @ClassRule
    public static DropwizardAppRule<ArticleServiceConfiguration> rule =
            new DropwizardAppRule<>(ArticleServiceApplication.class, "");

    @Test
    public void getResources(){
        ArticleRepository repo = RepositoryFactory.createArticleRepository();
        repo.deleteAll();
        repo.save(new ArticleBean("Feuerwehr", "Lego"));

        given().
                param("name", "Feuerwehr").
                when().
                get("/articles").
                then().
                assertThat().
                statusCode(200).
                and().
                body(equalTo("[{\"name\":\"Feuerwehr\"}]"));
    }
}
