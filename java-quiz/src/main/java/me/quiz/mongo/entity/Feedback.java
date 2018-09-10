package me.quiz.mongo.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Feedback {

    @Id
    private final ObjectId id;

    @Field("f")
    private final String feedback;

    public Feedback(String feedback) {
        this(ObjectId.get(), feedback);
    }

    public Feedback(ObjectId id, String feedback) {
        this.id = id;
        this.feedback = feedback;
    }
}
