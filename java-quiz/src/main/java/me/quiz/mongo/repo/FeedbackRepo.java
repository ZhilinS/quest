package me.quiz.mongo.repo;

import me.quiz.mongo.entity.Feedback;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FeedbackRepo extends MongoRepository<Feedback, ObjectId> { }
