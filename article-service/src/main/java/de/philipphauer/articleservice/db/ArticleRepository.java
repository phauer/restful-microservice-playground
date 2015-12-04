package de.philipphauer.articleservice.db;

import de.philipphauer.articleservice.db.custom.CustomArticleRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ArticleRepository extends MongoRepository<ArticleBean, String>, CustomArticleRepository {

   ArticleBean findByName(String name);
   List<ArticleBean> findByCategory(String category);

}