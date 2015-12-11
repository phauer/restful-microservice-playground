package de.philipphauer.musicianservice.autoRestViaMongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "musician", path = "musician")
public interface MusicianRepository extends MongoRepository<Musician, String> {

    List<Musician> findByName(@Param("name") String name);
    List<Musician> findByCategory(@Param("category") String name);

}