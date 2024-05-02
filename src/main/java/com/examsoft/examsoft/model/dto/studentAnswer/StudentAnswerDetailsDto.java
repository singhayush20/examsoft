package com.examsoft.examsoft.model.dto.studentAnswer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentAnswerDetailsDto {
    private String studentToken;

    private String examToken;

    private List<StudentAnswerContentDetailsDto> studentAnswers;
}
