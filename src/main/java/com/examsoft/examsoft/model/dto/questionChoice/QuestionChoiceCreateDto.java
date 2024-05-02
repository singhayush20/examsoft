package com.examsoft.examsoft.model.dto.questionChoice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionChoiceCreateDto {
    private String choiceText;
    private Boolean isCorrectAnswer;
}
