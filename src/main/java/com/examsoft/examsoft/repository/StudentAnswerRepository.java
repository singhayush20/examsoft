package com.examsoft.examsoft.repository;

import com.examsoft.examsoft.model.entity.StudentAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentAnswerRepository extends JpaRepository<StudentAnswer, Long> {


    // @Query("""
    //         SELECT * FROM StudentAnswer sa
    //         WHERE sa.student.studentToken = :?1
    //         AND sa.question.exam.examToken = :?2
    //         ORDER BY sa.question.sequenceNo ASC
    //         """)
    // List<StudentAnswer> findAllByStudentAndExamToken (String studentToken, String examToken);
}