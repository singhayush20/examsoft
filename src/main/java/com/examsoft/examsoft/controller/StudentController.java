package com.examsoft.examsoft.controller;

import com.examsoft.examsoft.model.dto.student.StudentCreateDto;
import com.examsoft.examsoft.service.StudentService;
import com.examsoft.examsoft.util.responseUtil.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/student")
public class StudentController {
    private final StudentService studentService;

    @PostMapping("/new")
    public ResponseEntity<ApiResponse<String>> registerStudent(@RequestBody StudentCreateDto studentCreateDto) {
        String token= studentService.createStudent(studentCreateDto);
        return new ResponseEntity<>(new ApiResponse<>(token), HttpStatus.CREATED);
    }
}
