package me.quiz.rest.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FeedbackReq {

    @JsonProperty("feedback")
    public String feedback;

    public String feedback() {
        return feedback;
    }
}
