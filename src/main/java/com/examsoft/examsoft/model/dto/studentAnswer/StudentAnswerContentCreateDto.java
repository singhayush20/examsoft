package com.examsoft.examsoft.model.dto.studentAnswer;

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
public class StudentAnswerContentCreateDto {


    private String questionToken;

    private List<String> descriptiveAnswer;

    private List<String> choiceTokens=new ArrayList<>();  //-selected choices

    private String fillAnswer; //-fill-type answer
}
