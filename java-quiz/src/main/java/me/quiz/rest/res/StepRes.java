package me.quiz.rest.res;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StepRes {

    @JsonProperty("number")
    public int number;

    @JsonProperty("success")
    public boolean success;

    public StepRes() {
        this(0, false);
    }

    public StepRes(
            int number,
            boolean success
    ) {
        this.number = number;
        this.success = success;
    }
}
