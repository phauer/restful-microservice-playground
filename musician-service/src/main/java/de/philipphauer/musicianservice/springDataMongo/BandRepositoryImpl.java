package de.philipphauer.musicianservice.springDataMongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.regex.Pattern;

public class BandRepositoryImpl implements BandRepositoryCustom{

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Band> findStartingWith(String prefix) {
        Query query = new Query(Criteria.where("name").regex(Pattern.compile(prefix+".*")));
        List<Band> bands = mongoTemplate.find(query, Band.class);
        return bands;
    }
}
