package de.philipphauer.musicianservice.db;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BandRepository extends MongoRepository<Band, String> {

    Band findByName(String name);
    List<Band> findByYearOfFoundation(int yearOfFoundation);

}