package de.philipphauer.musicianservice.autoRestViaMongo;

import org.springframework.data.annotation.Id;

public class Musician {

    @Id
    private String id;

    private String name;
    private String category;

    public Musician(){}

    public Musician(String name, String category) {
        this.name = name;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

}
