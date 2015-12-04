package de.philipphauer.articleservice.db;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ArticleRepository extends MongoRepository<ArticleBean, String> {

   ArticleBean findByName(String name);
   List<ArticleBean> findByCategory(String category);

}