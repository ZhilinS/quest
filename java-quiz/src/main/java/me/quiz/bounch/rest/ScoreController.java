package me.quiz.bounch.rest;

import me.quiz.bounch.mongo.entity.Step;
import me.quiz.bounch.mongo.repo.StepsRepo;
import me.quiz.bounch.rest.res.ScoreRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/score")
public class ScoreController {

    @Autowired StepsRepo stepsRepo;

    @SuppressWarnings("all")
    @GetMapping("current")
    public ScoreRes score() {
        final List<Step> steps = stepsRepo.findAll();
        final Optional<Integer> max = steps.stream()
                .filter(Step::success)
                .map(Step::stepNumber)
                .max(Integer::compareTo);

        if (max.isPresent()) {
            return new ScoreRes(max.get());
        }

        return new ScoreRes();
    }
}
