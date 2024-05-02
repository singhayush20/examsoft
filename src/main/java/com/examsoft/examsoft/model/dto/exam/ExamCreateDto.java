package com.examsoft.examsoft.model.dto.exam;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExamCreateDto {

    private String title;

    private String description;

}
