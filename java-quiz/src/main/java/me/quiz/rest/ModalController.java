package me.quiz.rest;

import me.quiz.mongo.entity.Quiz;
import me.quiz.mongo.entity.Score;
import me.quiz.mongo.entity.Step;
import me.quiz.mongo.repo.QuizRepo;
import me.quiz.mongo.repo.ScoreRepo;
import me.quiz.mongo.repo.StepsRepo;
import me.quiz.rest.req.TypedNumber;
import me.quiz.rest.res.CurrentModalRes;
import me.quiz.rest.res.StepRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("api/modal")
public class ModalController {

    @Autowired StepsRepo stepsRepo;
    @Autowired ScoreRepo scoreRepo;
    @Autowired QuizRepo quizRepo;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("submit")
    public void submit(@RequestBody TypedNumber number) {
        stepsRepo.save(
                new Step(number.step)
        );

        if (scoreRepo.count() == 0) {
            scoreRepo.save(new Score());
        }

        final Score score = scoreRepo.findAll().get(0);
        final Collection<Score.Answered> answered = score.answered();

        if (number.step == 2) {
            final long correctAnswers = quizRepo.findAll()
                    .stream()
                    .filter(Quiz::correct)
                    .count();

            answered.add(
                    new Score.Answered(
                            number.step,
                            (int) correctAnswers * 10
                    )
            );

            scoreRepo.save(
                    new Score(
                            score.id(),
                            answered
                    )
            );
        } else {
            answered.add(
                    new Score.Answered(
                            number.step,
                            100
                    )
            );

            scoreRepo.save(
                    new Score(
                            score.id(),
                            answered
                    )
            );
        }
    }

    @GetMapping("step/{number}")
    public StepRes step(@PathVariable("number") int number) {
        final Step step = stepsRepo.findByStepNumber(number);

        if (isNull(step)) {
            return new StepRes(
                    number,
                    false
            );
        }

        return new StepRes(
                step.stepNumber(),
                step.success()
        );
    }

    @GetMapping("current")
    public CurrentModalRes currentModal() {
        final Optional<Integer> max = stepsRepo.findAll()
                .stream()
                .filter(Step::success)
                .map(Step::stepNumber)
                .max(Integer::compareTo);

        if (max.isPresent()) {
            return new CurrentModalRes(max.get() + 1);
        }

        return new CurrentModalRes();
    }
}
