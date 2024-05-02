package com.examsoft.examsoft.repository;

import com.examsoft.examsoft.model.entity.ExamQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ExamQuestionRepository extends JpaRepository<ExamQuestion, Long> {

    @Query("SELECT e FROM ExamQuestion e WHERE e.questionToken = ?1")
    Optional<ExamQuestion> findByQuestionToken (String questionToken);


    @Query("SELECT e FROM ExamQuestion e WHERE e.exam.examToken = ?1 ORDER BY e.sequenceNo ASC")
    List<ExamQuestion> findAllByExamToken (String examToken);
}