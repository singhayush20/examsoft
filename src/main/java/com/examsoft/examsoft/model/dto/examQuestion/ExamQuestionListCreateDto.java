package com.examsoft.examsoft.model.dto.examQuestion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExamQuestionListCreateDto {
    private String examToken;

    List<ExamQuestionCreateDto> examQuestions;
}
