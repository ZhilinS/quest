package me.quiz.bounch.rest;

import me.quiz.bounch.mongo.entity.Step;
import me.quiz.bounch.mongo.repo.StepsRepo;
import me.quiz.bounch.rest.req.TypedNumber;
import me.quiz.bounch.rest.res.StepRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("api/modal")
public class ModalController {

    @Autowired StepsRepo stepsRepo;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("submit")
    public void submit(@RequestBody TypedNumber number) {
        System.out.println("GOT " + number.enteredNumber + " number from vue");
        stepsRepo.save(
                new Step(number.step)
        );
    }

    @GetMapping("step/{number}")
    public StepRes step(@PathVariable("number") int number) {
        System.out.println("INSIDE STEP " + number);
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
}
