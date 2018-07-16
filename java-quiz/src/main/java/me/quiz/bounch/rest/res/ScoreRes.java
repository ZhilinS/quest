package me.quiz.bounch.rest.res;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ScoreRes {

    private final int step;

    public ScoreRes() {
        this(0);
    }

    public ScoreRes(int step) {
        this.step = step;
    }

    @JsonProperty("score")
    public int score() {
        return step * 100;
    }
}
