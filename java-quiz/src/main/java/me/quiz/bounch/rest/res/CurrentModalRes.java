package me.quiz.bounch.rest.res;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentModalRes {

    private final int step;

    public CurrentModalRes() {
        this(0);
    }

    public CurrentModalRes(int step) {
        this.step = step;
    }

    @JsonProperty("step")
    public int step() {
        return step;
    }
}
