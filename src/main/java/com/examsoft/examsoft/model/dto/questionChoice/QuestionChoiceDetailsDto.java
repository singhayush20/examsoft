package com.examsoft.examsoft.model.dto.questionChoice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionChoiceDetailsDto {

    private String token;

    private String choiceText;

    private Boolean isCorrectAnswer;

    private Integer sequenceNo;
}
