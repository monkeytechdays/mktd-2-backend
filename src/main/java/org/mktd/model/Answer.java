package org.mktd.model;

import java.util.Objects;

public class Answer {

    private final Monkey monkey;

    public Answer(Monkey monkey) {
        super();
        this.monkey = monkey;
    }

    @Override
    public String toString() {
        return String.format("Answer{monkey=%s}", monkey);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return Objects.equals(monkey, answer.monkey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(monkey);
    }

    public Monkey getMonkey() {
        return monkey;
    }
}
