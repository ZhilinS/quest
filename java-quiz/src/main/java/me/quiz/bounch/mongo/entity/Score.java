package me.quiz.bounch.mongo.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.Collection;

@Document
public class Score {

    @Id
    public ObjectId id;

    @Field("a")
    public Collection<Answered> answered;

    public Score() {
        this(ObjectId.get(), new ArrayList());
    }

    public Score(Score old) {
        this(old.id(), old.answered());
    }

    public Score(
            ObjectId id,
            Collection<Answered> answered
    ) {
        this.id = id;
        this.answered = answered;
    }

    public static class Answered {

        @Field("s")
        public int stepId;

        @Field("p")
        public int points;

        public Answered() {
            this(0, 0);
        }

        public Answered(
                int stepId,
                int points
        ) {
            this.stepId = stepId;
            this.points = points;
        }

        public int stepId() {
            return stepId;
        }

        public int points() {
            return points;
        }
    }

    public ObjectId id() {
        return id;
    }

    public Collection<Answered> answered() {
        return answered;
    }
}
