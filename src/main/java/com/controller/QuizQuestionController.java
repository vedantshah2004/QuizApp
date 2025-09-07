package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.entity.QuizQuestionEntity;
import com.repository.QuizQuestionRepository;

@RestController
public class QuizQuestionController {

	@Autowired 
    QuizQuestionRepository quizQuestionRepo;

    @PostMapping("addQuizQuestion")
    public String addQuizQuestion(@RequestBody QuizQuestionEntity quizQuestion)
    {
        quizQuestionRepo.save(quizQuestion);
        return "Quiz Question added successfully!";
    }
    
    
    
    @DeleteMapping("removeQuizQuestion/{quizQuestionId}")
    public String removeQuizQuestion(@PathVariable  Integer quizQuestionId)
    {
        if (quizQuestionRepo.findById(quizQuestionId).isPresent())
        {
            quizQuestionRepo.deleteById(quizQuestionId);
            return "Quiz Question " + quizQuestionId + " deleted successfully!";
        } 
        else
        {
            return "  Quiz id  not found";
        }
    }
    
    
    
    @PostMapping("updateQuizQuestion/{quizQuestionId}")
    public String updateQuizQuestion(@PathVariable Integer quizQuestionId, @RequestBody QuizQuestionEntity updateQuestion) 
    {
        Optional<QuizQuestionEntity> presentQuestion = quizQuestionRepo.findById(quizQuestionId);
        if (presentQuestion.isPresent()) 
        {
            QuizQuestionEntity existingQuestion = presentQuestion.get();
            existingQuestion.setQuizId(updateQuestion.getQuizId());
            existingQuestion.setQuestion(updateQuestion.getQuestion());
            existingQuestion.setOption1(updateQuestion.getOption1());
            existingQuestion.setOption2(updateQuestion.getOption2());
            existingQuestion.setOption3(updateQuestion.getOption3());
            existingQuestion.setOption4(updateQuestion.getOption4());
            existingQuestion.setCorrectAns(updateQuestion.getCorrectAns());

            quizQuestionRepo.save(existingQuestion);
            return "Quiz Question updated successfully!";
        }
        else
        {
            return "invalid Quiz Question";
        }
    }
    
    @GetMapping("listAllQuizQuestionByQuizId/{quizId}")
    public List<QuizQuestionEntity> listAllQuizQuestionByQuizId(@PathVariable Integer quizId)
    {   
    	return quizQuestionRepo.findByQuizId(quizId);
    }
	
}
