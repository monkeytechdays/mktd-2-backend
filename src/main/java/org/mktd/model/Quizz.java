package org.mktd.model;

import java.util.List;
import java.util.Objects;

public class Quizz {
    private final String id;
    private final String userName;
    private final List<Question> questions;

    public Quizz(String id, String userName, List<Question> questions) {
        super();
        this.id = id;
        this.userName = userName;
        this.questions = questions;
    }

    @Override
    public String toString() {
        return String.format("Quizz{id='%s', userName='%s'}", id, userName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quizz quizz = (Quizz) o;
        return Objects.equals(id, quizz.id) &&
                Objects.equals(userName, quizz.userName) &&
                Objects.equals(questions, quizz.questions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, questions);
    }

    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public List<Question> getQuestions() {
        return questions;
    }
}
