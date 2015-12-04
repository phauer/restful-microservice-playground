package de.philipphauer.articleservice;

public class ArticleResponse {

    private String name;

    public ArticleResponse(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
