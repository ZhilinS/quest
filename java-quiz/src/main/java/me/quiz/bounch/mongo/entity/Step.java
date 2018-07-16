package me.quiz.bounch.mongo.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Step {

    @Id
    private ObjectId id;

    @Field("step")
    private int stepNumber;

    @Field("success")
    private boolean success;

    public Step() {
        this(ObjectId.get(), 0, false);
    }

    public Step(int stepNumber) {
        this(ObjectId.get(), stepNumber, true);
    }

    public Step(
            ObjectId id,
            int stepNumber,
            boolean success
    ) {
        this.id = id;
        this.stepNumber = stepNumber;
        this.success = success;
    }

    public int stepNumber() {
        return stepNumber;
    }

    public boolean success() {
        return success;
    }
}
