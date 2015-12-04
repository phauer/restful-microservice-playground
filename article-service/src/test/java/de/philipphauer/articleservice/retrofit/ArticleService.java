package de.philipphauer.articleservice.retrofit;

import de.philipphauer.articleservice.ArticleResponse;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

import java.util.List;

public interface  ArticleService {

    @GET("/articles")
    Call<List<ArticleResponse>> getAllArticles();

    @GET("/articles")
    Call<List<ArticleResponse>> getArticles(@Query("name") String name);
}
