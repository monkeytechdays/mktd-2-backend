package org.mktd.model;

import java.time.Duration;
import java.util.Comparator;
import java.util.Objects;

public class QuizzResult implements Comparable<QuizzResult> {

    public final Comparator<QuizzResult> COMPARATOR = Comparator.comparingInt(QuizzResult::getScore)
            .thenComparing(Comparator.comparingLong((QuizzResult result) -> result.getDuration().toMillis()));

    private final String userName;
    private final int score;
    private final Duration duration;


    public QuizzResult(String userName, int score, Duration duration) {
        super();
        this.userName = userName;
        this.score = score;
        this.duration = duration;
    }

    @Override
    public int compareTo(QuizzResult o) {
        return COMPARATOR.compare(this, o);
    }

    @Override
    public String toString() {
        return String.format("QuizzResult{userName='%s', score=%d, duration=%s}", userName, score, duration);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuizzResult that = (QuizzResult) o;
        return score == that.score &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(duration, that.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, score, duration);
    }

    public String getUserName() {
        return userName;
    }

    public int getScore() {
        return score;
    }

    public Duration getDuration() {
        return duration;
    }
}
