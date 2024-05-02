package com.examsoft.examsoft.repository;

import com.examsoft.examsoft.model.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
public interface ExamRepository extends JpaRepository<Exam, Long> {


    @Query("SELECT e FROM Exam e WHERE e.examToken = ?1")
    Optional<Exam> findByExamToken (String examToken);
}