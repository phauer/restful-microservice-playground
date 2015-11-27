package de.philipphauer.articleservice.db;

import org.springframework.data.annotation.Id;

/**
 * Created by hauer on 27.11.2015.
 */
public class ArticleBean {

    @Id
    private String id;

    private String name;

    public ArticleBean() {}

    public ArticleBean(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Customer[id=%s, name='%s']", id, name);
    }

}