package de.philipphauer.musicianservice.rest;

public class MusicianResponse {

    private final String name;

    public MusicianResponse(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
