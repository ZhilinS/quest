package me.quiz.rest;

import me.quiz.mongo.entity.Quiz;
import me.quiz.mongo.repo.QuizRepo;
import me.quiz.mongo.repo.ScoreRepo;
import me.quiz.rest.req.QuizAnswer;
import me.quiz.rest.res.CurrentQuizRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/quiz")
public class QuizController {

    @Autowired QuizRepo quizRepo;
    @Autowired
    ScoreRepo scoreRepo;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("submit")
    public void acceptQuizResponse(@RequestBody QuizAnswer answer) {
        quizRepo.save(
                new Quiz(
                        answer.questionNumber,
                        answer.selected,
                        answer.correct
                )
        );
    }

    @GetMapping("current")
    public CurrentQuizRes currentQuizRes() {
        final Optional<Integer> finishedQuestions = quizRepo.findAll()
                .stream()
                .map(Quiz::id)
                .max(Integer::compareTo);

        if (finishedQuestions.isPresent()) {
            return new CurrentQuizRes(finishedQuestions.get() + 1);
        }

        return new CurrentQuizRes(1);
    }
}
