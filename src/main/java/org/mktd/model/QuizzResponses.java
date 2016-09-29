package org.mktd.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class QuizzResponses {
    private final List<String> responses;

    public QuizzResponses(List<String> responses) {
        super();
        this.responses = responses;
    }

    public QuizzResponses() {
        this(new ArrayList<>());
    }

    @Override
    public String toString() {
        return String.format("QuizzResponses{responses=%s}", responses);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuizzResponses that = (QuizzResponses) o;
        return Objects.equals(responses, that.responses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(responses);
    }

    public List<String> getResponses() {
        return responses;
    }
}
