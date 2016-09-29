package org.mktd.model;

import java.util.Objects;

public class Monkey {
    private final String race;
    private final Photo photo;

    public Monkey(String race, Photo photo) {
        super();
        this.race = race;
        this.photo = photo;
    }

    public Monkey(String race, String url, String attribution) {
        this(race, new Photo(url, attribution));
    }

    @Override
    public String toString() {
        return String.format("Monkey{race='%s', photo=%s}", race, photo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Monkey monkey = (Monkey) o;
        return Objects.equals(race, monkey.race) &&
                Objects.equals(photo, monkey.photo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(race, photo);
    }

    public String getRace() {
        return race;
    }

    public Photo getPhoto() {
        return photo;
    }
}
