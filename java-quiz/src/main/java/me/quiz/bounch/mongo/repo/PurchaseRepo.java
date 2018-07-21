package me.quiz.bounch.mongo.repo;

import me.quiz.bounch.mongo.entity.Purchase;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PurchaseRepo extends MongoRepository<Purchase, ObjectId> {

}
