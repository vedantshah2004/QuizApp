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

import com.entity.QuizEntity;
import com.repository.QuizRepository;
import com.repository.UserRepository;
import lombok.Getter;

@RestController
public class QuizController {

    @Autowired  UserRepository userRepository;
	
	@Autowired QuizRepository quizrepo;

	
	@PostMapping("addQuiz")
	public String addQuiz(@RequestBody QuizEntity quizentity)
	{
		quizrepo.save(quizentity);
		return "Quiz added";
	}
	
	@PostMapping("updateQuiz/{quizId}")
	public String updateQuiz(@PathVariable Integer quizId, @RequestBody QuizEntity updatequiz)
	{
	    Optional<QuizEntity> presentquiz = quizrepo.findById(quizId);

	    if (presentquiz.isPresent()) {
	        QuizEntity existingQuiz = presentquiz.get();
	        
	        existingQuiz.setTitle(updatequiz.getTitle());
	        existingQuiz.setDescription(updatequiz.getDescription());
	        existingQuiz.setActive(updatequiz.getActive());

	        quizrepo.save(existingQuiz);
	        return "Quiz updated successfully";
	    } else {
	        return "Quiz not found!";
	    }
	}

	
	@DeleteMapping("deleteQuiz/{quizId}")
	public String deleteQuiz(@PathVariable Integer quizId)
	{
		if(quizrepo.findById(quizId).isPresent())
		{	
			quizrepo.deleteById(quizId);
			return "Quiz  is Deleted";
	
		}
		else {
			return "quiz  is not present ";
		}
	}
	
	
	@GetMapping("listAllQuiz")
	public List<QuizEntity> listAllQuiz()
	{
		List<QuizEntity> quizlist=quizrepo.findAll();
		return quizlist;
	}
	
	
	@GetMapping("listAllActiveQuiz")
	public List<QuizEntity> listAllQuizActive()
	{
		List<QuizEntity> quizlist=quizrepo.findByActiveTrue();
		return quizlist;
	}
}
