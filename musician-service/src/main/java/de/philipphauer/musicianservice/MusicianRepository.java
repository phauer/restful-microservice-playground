package de.philipphauer.musicianservice;

import org.springframework.stereotype.Repository;

import java.util.Random;

@Repository
public class MusicianRepository {

    public int getRandomInteger(){
        return new Random().nextInt();
    }
}
