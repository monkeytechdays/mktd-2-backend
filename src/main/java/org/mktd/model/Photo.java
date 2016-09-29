package org.mktd.model;

import java.util.Objects;

public class Photo {
    private final String url;
    private final String attribution;

    public Photo(String url, String attribution) {
        super();
        this.url = url;
        this.attribution = attribution;
    }

    @Override
    public String toString() {
        return String.format("Photo{url='%s', attribution='%s'}", url, attribution);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photo photo = (Photo) o;
        return Objects.equals(url, photo.url) &&
                Objects.equals(attribution, photo.attribution);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, attribution);
    }

    public String getUrl() {
        return url;
    }

    public String getAttribution() {
        return attribution;
    }
}
