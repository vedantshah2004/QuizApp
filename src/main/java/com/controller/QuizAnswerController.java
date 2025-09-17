package com.controller;

import java.util.Optional;
import java.util.List;

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

    // User submits answer for a question
    @PostMapping("/submitQuizAnswer")
    public String submitQuizAnswer(@RequestBody QuizAnswerEntity answer) {

        // Fetch the question from quiz_questions table
        Optional<QuizQuestionEntity> questionOpt = quizQuestionRepo.findById(answer.getQuizQuestionId());
        if (questionOpt.isEmpty()) {
            return "Invalid quiz question ID!";
        }

        QuizQuestionEntity question = questionOpt.get();

        // Set quizId from the question
        answer.setQuizId(question.getQuizId());

        // Always set correct answer from question
        String correctAns = question.getCorrectAns();
        answer.setCorrectAns(correctAns);

        // Compare user's answer with correct answer
        if (answer.getSelectAns() != null && answer.getSelectAns().equalsIgnoreCase(correctAns)) {
            answer.setStatus("correct");
        } else {
            answer.setStatus("wrong");
        }

        // Save to DB
        quizAnswerRepo.save(answer);

        return "Answer submitted successfully!";
    }

    // Fetch all answers of a user for a quiz
    @GetMapping("/getUserQuizAnswers/{quizId}/{userId}")
    public List<QuizAnswerEntity> getUserQuizAnswers(@PathVariable Integer quizId,
                                                     @PathVariable Integer userId) {
        return quizAnswerRepo.findByQuizIdAndUserId(quizId, userId);
    }

    // Fetch user score (extra useful for result screen)
    @GetMapping("/getUserQuizResult/{quizId}/{userId}")
    public java.util.Map<String, Object> getUserQuizResult(@PathVariable Integer quizId,
                                                           @PathVariable Integer userId) {
        List<QuizAnswerEntity> answers = quizAnswerRepo.findByQuizIdAndUserId(quizId, userId);

        long correctCount = answers.stream()
                .filter(a -> a.getStatus() != null && a.getStatus().equalsIgnoreCase("correct"))
                .count();

        long totalQuestions = answers.size();
        long wrongCount = totalQuestions - correctCount;

        java.util.Map<String, Object> result = new java.util.HashMap<>();
        result.put("correct", correctCount);
        result.put("wrong", wrongCount);
        result.put("total", totalQuestions);
        result.put("percentage", totalQuestions > 0 ? (correctCount * 100 / totalQuestions) : 0);

        return result;
    }


    
    
    
    
    


}
