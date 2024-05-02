package com.examsoft.examsoft.model.dto.studentAnswer;

import com.examsoft.examsoft.model.constants.QuestionType;
import com.examsoft.examsoft.model.dto.questionChoice.QuestionChoiceDetailsDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentAnswerContentDetailsDto {
    private String questionToken;

    private QuestionType questionType;

    private String descriptiveAnswer;

    private List<QuestionChoiceDetailsDto> choiceTokens=new ArrayList<>();

    private String fillAnswer;
}
