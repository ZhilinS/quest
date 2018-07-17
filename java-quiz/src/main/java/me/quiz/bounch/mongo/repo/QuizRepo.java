package me.quiz.bounch.mongo.repo;

import me.quiz.bounch.mongo.entity.Quiz;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuizRepo extends MongoRepository<Quiz, ObjectId> {

}
