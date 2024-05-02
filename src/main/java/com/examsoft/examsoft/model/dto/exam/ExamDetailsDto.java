package com.examsoft.examsoft.model.dto.exam;

import com.examsoft.examsoft.model.dto.examQuestion.ExamQuestionDetailsDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExamDetailsDto {
    private String examToken;
    private String title;
    private String description;
    private List<ExamQuestionDetailsDto> questions;
}
