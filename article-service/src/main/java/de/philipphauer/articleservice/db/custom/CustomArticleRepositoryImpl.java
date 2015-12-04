package de.philipphauer.articleservice.db.custom;

import com.google.common.collect.Lists;
import de.philipphauer.articleservice.db.ArticleBean;

import java.util.List;

public class CustomArticleRepositoryImpl implements CustomArticleRepository {

    /*
     * without DI this doesn't make fun. :-(
     */

    @Override
    public List<ArticleBean> findAllLegoArticles() {
        return Lists.newArrayList();
    }
}
