package me.quiz.rest;

import me.quiz.mongo.entity.Score;
import me.quiz.mongo.repo.ScoreRepo;
import me.quiz.mongo.repo.StepsRepo;
import me.quiz.rest.res.ScoreRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("api/score")
public class ScoreController {

    @Autowired StepsRepo stepsRepo;
    @Autowired ScoreRepo scoreRepo;

    @SuppressWarnings("all")
    @GetMapping("current")
    public ScoreRes score() {
        final int score = scoreRepo.findAll()
                .stream()
                .map(Score::answered)
                .flatMap(Collection::stream)
                .map(Score.Answered::points)
                .mapToInt(Integer::valueOf)
                .sum();

        return new ScoreRes(score);
    }
}
