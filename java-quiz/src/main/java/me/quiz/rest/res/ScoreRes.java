package me.quiz.rest.res;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ScoreRes {

    private final int score;

    public ScoreRes() {
        this(0);
    }

    public ScoreRes(int score) {
        this.score = score;
    }

    @JsonProperty("score")
    public int score() {
        return score;
    }
}
