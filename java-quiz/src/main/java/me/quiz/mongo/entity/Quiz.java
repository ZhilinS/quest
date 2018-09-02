package me.quiz.mongo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Quiz {

    @Id
    private final int id;

    @Field("c")
    private final boolean correct;

    @Field("s")
    private final int selected;

    public Quiz() {
        this(0, 0, false);
    }

    public Quiz(
            int id,
            int selected,
            boolean correct
    ) {
        this.id = id;
        this.selected = selected;
        this.correct = correct;
    }

    public int id() {
        return id;
    }

    public boolean correct() {
        return correct;
    }

    public int selected() {
        return selected;
    }
}
