package me.quiz.bounch.rest.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PurchaseReq {

    @JsonProperty("items")
    public Collection<SelectedItem> items;

    @JsonProperty("sum")
    public int sum;

    public static class SelectedItem {

        @JsonProperty("id")
        public int id;

        @JsonProperty("description")
        public String description;

        @JsonProperty("cost")
        public int cost;
    }
}
