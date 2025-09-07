package com.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "quiz")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuizEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer quizId;
	
	String title;
	String description;
	LocalDateTime createdAt=LocalDateTime.now();;
	Boolean active;

}
