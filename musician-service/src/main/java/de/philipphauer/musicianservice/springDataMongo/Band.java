package de.philipphauer.musicianservice.springDataMongo;

import com.google.common.base.MoreObjects;
import org.springframework.data.annotation.Id;

public class Band {
    @Id
    private String id;

    private String name;
    private int yearOfFoundation;

    public Band() {}

    public Band(String name, int yearOfFoundation) {
        this.name = name;
        this.yearOfFoundation = yearOfFoundation;
    }

    public String getName() {
        return name;
    }

    public int getYearOfFoundation() {
        return yearOfFoundation;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("yearOfFoundation", yearOfFoundation)
                .toString();
    }
}
