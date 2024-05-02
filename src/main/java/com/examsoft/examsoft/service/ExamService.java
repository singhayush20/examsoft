package com.examsoft.examsoft.service;

import com.examsoft.examsoft.model.dto.exam.ExamCreateDto;
import com.examsoft.examsoft.model.dto.exam.ExamDetailsDto;
import com.examsoft.examsoft.model.dto.examQuestion.ExamQuestionListCreateDto;
import com.examsoft.examsoft.model.dto.studentAnswer.StudentAnswerListCreateDto;

import java.util.List;
import java.util.Set;

public interface ExamService {

    String createExam(ExamCreateDto examCreateDto);

    Set<String> createExamQuestion(ExamQuestionListCreateDto examQuestionCreateDto);

    List<String> saveExamAnswers (StudentAnswerListCreateDto answers);

    ExamDetailsDto examDetails (String examToken);
}
