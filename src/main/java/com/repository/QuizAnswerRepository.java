package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.QuizAnswerEntity;

public interface QuizAnswerRepository extends JpaRepository<QuizAnswerEntity, Integer> {

    List<QuizAnswerEntity> findByQuizIdAndUserId(Integer quizId, Integer userId);
}
