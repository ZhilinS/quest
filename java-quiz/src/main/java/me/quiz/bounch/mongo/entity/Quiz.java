package me.quiz.bounch.mongo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Quiz {

    @Id
    private int id;

    @Field("c")
    public boolean correct;

    public Quiz() {
        this(0, false);
    }

    public Quiz(
            int id,
            boolean correct
    ) {
        this.id = id;
        this.correct = correct;
    }

    public int id() {
        return id;
    }
}
