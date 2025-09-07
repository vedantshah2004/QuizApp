package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.QuizEntity;

public interface QuizRepository extends JpaRepository<QuizEntity, Integer> {

	List<QuizEntity> findByActiveTrue();
}
