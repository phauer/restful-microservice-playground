package de.philipphauer.articleservice;

import de.philipphauer.articleservice.db.ArticleBean;
import de.philipphauer.articleservice.db.ArticleRepository;
import de.philipphauer.articleservice.db.RepositoryFactory;
import de.philipphauer.articleservice.retrofit.ArticleService;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.ClassRule;
import org.junit.Test;
import retrofit.Call;
import retrofit.JacksonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

import java.io.IOException;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ArticleResourceIntegrationTest {

    @ClassRule
    public static DropwizardAppRule<ArticleServiceConfiguration> rule =
            new DropwizardAppRule<>(ArticleServiceApplication.class, "");

    @Test
    public void getResourcesRetroFit() throws IOException {
        ArticleRepository repo = RepositoryFactory.createArticleRepository();
        repo.deleteAll();
        repo.save(new ArticleBean("Feuerwehr", "Lego"));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        ArticleService service = retrofit.create(ArticleService.class);
        Call<List<ArticleResponse>> articles = service.getAllArticles();
        Response<List<ArticleResponse>> response = articles.execute();
        assertThat(response.isSuccess()).isTrue();

        List<ArticleResponse> articleResponses = response.body();
        assertThat(articleResponses)
                .hasSize(1);
    }

    @Test
    public void getResourcesRestAssured(){
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
