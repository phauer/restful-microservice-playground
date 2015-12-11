package de.philipphauer.musicianservice.springDataMongo;

import java.util.List;

public interface BandRepositoryCustom {
    List<Band> findStartingWith(String prefix);
}
