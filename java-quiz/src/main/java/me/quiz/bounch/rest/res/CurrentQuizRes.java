package me.quiz.bounch.rest.res;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentQuizRes {

    private final int num;

    public CurrentQuizRes(int num) {
        this.num = num;
    }

    @JsonProperty("num")
    public int num() {
        return num;
    }
}
