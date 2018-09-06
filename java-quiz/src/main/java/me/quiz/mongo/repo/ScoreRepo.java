package me.quiz.mongo.repo;

import me.quiz.mongo.entity.Score;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ScoreRepo extends MongoRepository<Score, ObjectId> { }
