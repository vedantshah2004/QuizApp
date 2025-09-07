package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.QuizQuestionEntity;

public interface QuizQuestionRepository  extends JpaRepository<QuizQuestionEntity, Integer>{
    List<QuizQuestionEntity> findByQuizId(Integer quizId);
    
}
