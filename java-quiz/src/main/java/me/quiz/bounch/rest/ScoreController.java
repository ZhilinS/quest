package me.quiz.bounch.rest;

import me.quiz.bounch.mongo.entity.Score;
import me.quiz.bounch.mongo.entity.Step;
import me.quiz.bounch.mongo.repo.ScoreRepo;
import me.quiz.bounch.mongo.repo.StepsRepo;
import me.quiz.bounch.rest.res.ScoreRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

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
