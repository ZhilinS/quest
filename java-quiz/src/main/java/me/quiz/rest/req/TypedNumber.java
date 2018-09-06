package me.quiz.rest.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TypedNumber {

    @JsonProperty("step")
    public int step;

    @JsonProperty("entered_number")
    public int enteredNumber;
}
