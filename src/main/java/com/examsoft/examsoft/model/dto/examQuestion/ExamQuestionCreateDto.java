package com.examsoft.examsoft.model.dto.examQuestion;

import com.examsoft.examsoft.model.constants.QuestionType;
import com.examsoft.examsoft.model.dto.questionChoice.QuestionChoiceCreateDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExamQuestionCreateDto {
    private String question;


    private QuestionType questionType;

    private Set<QuestionChoiceCreateDto> questionChoices=new HashSet<>();
}
