package org.mktd.model;

import java.util.Objects;

public class Photo {
    private final String dataUri;
    private final String attribution;

    public Photo(String dataUri, String attribution) {
        super();
        this.dataUri = dataUri;
        this.attribution = attribution;
    }

    @Override
    public String toString() {
        return String.format("Photo{dataUri='%s', attribution='%s'}", dataUri, attribution);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photo photo = (Photo) o;
        return Objects.equals(dataUri, photo.dataUri) &&
                Objects.equals(attribution, photo.attribution);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataUri, attribution);
    }

    public String getDataUri() {
        return dataUri;
    }

    public String getAttribution() {
        return attribution;
    }
}
