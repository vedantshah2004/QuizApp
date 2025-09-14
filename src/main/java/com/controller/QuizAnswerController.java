package com.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.entity.QuizAnswerEntity;
import com.entity.QuizQuestionEntity;
import com.repository.QuizAnswerRepository;
import com.repository.QuizQuestionRepository;

@RestController
@RequestMapping("/api")  // Base path for all methods
public class QuizAnswerController {

    @Autowired
    QuizAnswerRepository quizAnswerRepo;

    @Autowired
    QuizQuestionRepository quizQuestionRepo;

    @PostMapping("/submitQuizAnswer")
    public String submitQuizAnswer(@RequestBody QuizAnswerEntity answer) {

        // Fetch the question from quiz_questions table
        Optional<QuizQuestionEntity> questionOpt = quizQuestionRepo.findById(answer.getQuizQuestionId());
        if (questionOpt.isEmpty()) {
            return "Invalid quiz question ID!";
        }

        QuizQuestionEntity question = questionOpt.get();

        // Set correct answer
        String correctAns = question.getCorrectAns();
        answer.setCorrectAns(correctAns);

        // Set status
        if (answer.getSelectAns() != null && answer.getSelectAns().equals(correctAns)) {
            answer.setStatus(correctAns);
        } else {
            answer.setStatus("wrong");
        }

        // **Set quizId from the question**
        answer.setQuizId(question.getQuizId());

        // Save answer
        quizAnswerRepo.save(answer);

        return "Answer submitted successfully!";
    }


    @GetMapping("/getUserQuizAnswers/{quizId}/{userId}")
    public java.util.List<QuizAnswerEntity> getUserQuizAnswers(@PathVariable Integer quizId,
                                                               @PathVariable Integer userId) {
        return quizAnswerRepo.findByQuizIdAndUserId(quizId, userId);
    }
}
