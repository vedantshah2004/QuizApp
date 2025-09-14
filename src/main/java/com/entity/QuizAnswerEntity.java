package com.entity;

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
@Table(name = "quiz_ans")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuizAnswerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer quizAnsId;

    Integer quizId;           // FK to quiz
    Integer quizQuestionId;   // FK to quiz_questions
    String selectAns;         // Selected answer by user
    String correctAns;        // Correct answer from question
    String status;            // "correct" or "wrong"
    Integer userId;           // FK to user
}
