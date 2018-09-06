package me.quiz.mongo.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.Collection;

@Document
public class Purchase {

    @Id
    private final ObjectId id;

    @Field("t")
    private final Collection<PurchasedItem> items;

    @Field("s")
    private final int sum;

    public Purchase() {
        this(ObjectId.get(), new ArrayList<>(), 0);
    }

    public Purchase(
            Collection<PurchasedItem> items,
            int sum
    ) {
        this(ObjectId.get(), items, sum);
    }

    public Purchase(
            ObjectId id,
            Collection<PurchasedItem> items,
            int sum
    ) {
        this.id = id;
        this.items = items;
        this.sum = sum;
    }

    public static class PurchasedItem {
        @Field("id")
        private final int id;

        @Field("d")
        private final String description;

        @Field("c")
        private final int cost;

        public PurchasedItem() {
            this(0, "", 0);
        }

        public PurchasedItem(
                int id,
                String description,
                int cost
        ) {
            this.id = id;
            this.description = description;
            this.cost = cost;
        }
    }
}
