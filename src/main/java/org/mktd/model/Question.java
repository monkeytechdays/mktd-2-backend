package org.mktd.model;

import java.util.List;
import java.util.Objects;

public class Question {

    private final List<String> responses;

    public Question(List<String> responses) {
        super();
        this.responses = responses;
    }

    @Override
    public String toString() {
        return String.format("Question{responses=%s}", responses);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(responses, question.responses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(responses);
    }

    public List<String> getResponses() {
        return responses;
    }
}
