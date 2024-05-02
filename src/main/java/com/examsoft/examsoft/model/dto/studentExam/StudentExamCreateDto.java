package com.examsoft.examsoft.model.dto.studentExam;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentExamCreateDto {

    private String examToken;

    private String studentToken;
}
