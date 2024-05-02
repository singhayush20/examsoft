package com.examsoft.examsoft.service;

import com.examsoft.examsoft.model.dto.studentAnswer.StudentAnswerDetailsDto;

import java.util.List;

public interface ExamAnswerService {
    List<StudentAnswerDetailsDto> getAnswersForExamAndStudent(String examToken, String studentToken);
}
