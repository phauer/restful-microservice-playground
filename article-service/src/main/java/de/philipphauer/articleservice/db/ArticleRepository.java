package de.philipphauer.articleservice.db;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by hauer on 27.11.2015.
 */
public interface ArticleRepository extends MongoRepository<ArticleBean, String> {

    public ArticleBean findByName(String name);
   // public List<ArticleBean> findByName(String lastName);

}