package com.examsoft.examsoft.controller;

import com.examsoft.examsoft.model.dto.exam.ExamCreateDto;
import com.examsoft.examsoft.model.dto.exam.ExamDetailsDto;
import com.examsoft.examsoft.model.dto.examQuestion.ExamQuestionListCreateDto;
import com.examsoft.examsoft.model.dto.studentAnswer.StudentAnswerListCreateDto;
import com.examsoft.examsoft.service.ExamService;
import com.examsoft.examsoft.util.responseUtil.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/exam")
public class ExamController {

    private final ExamService examService;

    @PostMapping("/new")
    public ResponseEntity<ApiResponse<String>> createExam (@RequestBody ExamCreateDto examCreateDto) {
        String token = examService.createExam(examCreateDto);
        return new ResponseEntity<>(new ApiResponse<>(token), HttpStatus.CREATED);
    }

    @PostMapping("/questions/new")
    public ResponseEntity<ApiResponse<Set<String>>> createExamQuestion (@RequestBody ExamQuestionListCreateDto questionDto) {
        Set<String> examQuestionCreateDtos = examService.createExamQuestion(questionDto);
        return new ResponseEntity<>(new ApiResponse<>(examQuestionCreateDtos), HttpStatus.CREATED);
    }

    @PostMapping("/answers")
    public ResponseEntity<ApiResponse<List<String>>> createExamAnswers (@RequestBody StudentAnswerListCreateDto answers) {
        List<String> answerTokens = examService.saveExamAnswers(answers);
        return new ResponseEntity<>(new ApiResponse<>(answerTokens), HttpStatus.CREATED);
    }

    @GetMapping("/{examToken}")
    public ResponseEntity<ApiResponse<ExamDetailsDto>> examDetails (@PathVariable String examToken) {
        ExamDetailsDto examDetailsDto = examService.examDetails(examToken);
        return new ResponseEntity<>(new ApiResponse<>(examDetailsDto), HttpStatus.OK);
    }
}
