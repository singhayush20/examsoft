package com.examsoft.examsoft.model.dto.student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentCreateDto {

    private String name;

    private Long enrollmentNo;

    private String gender;

    private Date dob;

    private Long mobileNo;
}
