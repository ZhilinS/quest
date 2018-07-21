package me.quiz.bounch.mongo.repo;

import me.quiz.bounch.mongo.entity.Score;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ScoreRepo extends MongoRepository<Score, ObjectId> { }
