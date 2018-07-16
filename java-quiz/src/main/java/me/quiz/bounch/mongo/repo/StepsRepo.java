package me.quiz.bounch.mongo.repo;

import me.quiz.bounch.mongo.entity.Step;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface StepsRepo extends MongoRepository<Step, ObjectId> {

    @Query("{ 'step': ?0 }")
    Step findByStepNumber(int number);
}
