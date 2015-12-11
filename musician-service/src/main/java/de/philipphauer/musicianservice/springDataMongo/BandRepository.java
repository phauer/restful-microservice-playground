package de.philipphauer.musicianservice.springDataMongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BandRepository extends MongoRepository<Band, String>, BandRepositoryCustom {

    Band findByName(String name);
    List<Band> findByYearOfFoundation(int yearOfFoundation);

}