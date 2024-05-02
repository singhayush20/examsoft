package com.examsoft.examsoft.repository;

import com.examsoft.examsoft.model.entity.StudentExam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentExamRepository extends JpaRepository<StudentExam, Long> {

}