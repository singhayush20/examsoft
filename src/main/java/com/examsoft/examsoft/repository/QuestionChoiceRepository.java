package com.examsoft.examsoft.repository;

import com.examsoft.examsoft.model.entity.QuestionChoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface QuestionChoiceRepository extends JpaRepository<QuestionChoice, Long> {

    @Query("SELECT q FROM QuestionChoice q WHERE q.choiceToken = ?1 AND q.question.questionToken = ?2")
    Optional<QuestionChoice> findByChoiceTokenAndQuestionToken (String choiceToken, String questionToken);

    @Query("SELECT q FROM QuestionChoice q WHERE q.question.questionToken = ?1")
    List<QuestionChoice> findAllByQuestionToken (String questionToken);
}