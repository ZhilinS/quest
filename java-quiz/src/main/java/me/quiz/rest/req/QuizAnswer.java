package me.quiz.rest.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QuizAnswer {

    @JsonProperty("question_number")
    public int questionNumber;

    @JsonProperty("selected")
    public int selected;

    @JsonProperty("correct")
    public boolean correct;
}
