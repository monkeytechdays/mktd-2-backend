package org.mktd.model;

import java.util.List;
import java.util.Objects;

public class QuizzAnswers {

    private final String userName;
    private final List<Answer> answers;
    private final long startAt;

    public QuizzAnswers(String userName, List<Answer> answers) {
        super();
        this.userName = userName;
        this.answers = answers;
        this.startAt = System.nanoTime();
    }

    @Override
    public String toString() {
        return String.format("QuizzAnswers{userName='%s', answers=%s}", userName, answers);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuizzAnswers that = (QuizzAnswers) o;
        return startAt == that.startAt &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(answers, that.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, answers, startAt);
    }

    public String getUserName() {
        return userName;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public long getStartAt() {
        return startAt;
    }
}
