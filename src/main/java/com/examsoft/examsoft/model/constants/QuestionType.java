package com.examsoft.examsoft.model.constants;

import lombok.Getter;

@Getter
public enum QuestionType {

    MULTIPLE_CHOICE("Multiple Choice"),
    SINGLE_CHOICE("Single Choice"),
    FILL("Fill"),
    DESCRIPTIVE("Descriptive");

    QuestionType (String value) {
        this.value = value;
    }

    private String value;
}
