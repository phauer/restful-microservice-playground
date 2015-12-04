package de.philipphauer.articleservice.db.custom;

import de.philipphauer.articleservice.db.ArticleBean;

import java.util.List;

public interface CustomArticleRepository {

    List<ArticleBean> findAllLegoArticles();
}
