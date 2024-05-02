package com.examsoft.examsoft.model.dto.examQuestion;

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
public class ExamQuestionDetailsDto {

    private String questionToken;

    private String question;

    private Integer sequenceNo;

    private List<String> imageUrls=new ArrayList<>();

    private QuestionType questionType;

    private List<QuestionChoiceDetailsDto> questionChoices=new ArrayList<>();

    private String status="pending"; //-required on frontend
}
