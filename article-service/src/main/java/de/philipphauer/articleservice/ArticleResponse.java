package de.philipphauer.articleservice;

/**
 * Created by hauer on 27.11.2015.
 */
public class ArticleResponse {

    private String name;

    public ArticleResponse(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
