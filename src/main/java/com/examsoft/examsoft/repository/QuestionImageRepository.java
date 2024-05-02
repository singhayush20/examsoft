package com.examsoft.examsoft.repository;

import com.examsoft.examsoft.model.entity.QuestionImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionImageRepository extends JpaRepository<QuestionImage, Long> {

    @Query("SELECT e.imageUrl FROM QuestionImage e WHERE e.question.questionToken = ?1 ORDER BY createdAt ASC")
    List<String> findAllByQuestionToken (String questionToken);
}