package com.examsoft.examsoft.repository;

import com.examsoft.examsoft.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE s.studentToken = ?1")
    Optional<Student> findByToken (String studentToken);
}