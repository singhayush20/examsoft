package com.examsoft.examsoft.service.serviceImpl;

import com.examsoft.examsoft.model.dto.student.StudentCreateDto;
import com.examsoft.examsoft.model.entity.Student;
import com.examsoft.examsoft.repository.StudentRepository;
import com.examsoft.examsoft.service.StudentService;
import lombok.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;


    @Override
    public String createStudent (StudentCreateDto studentCreateDto) {
        Student student=new Student();
        student.setName(studentCreateDto.getName());
        student.setDob(studentCreateDto.getDob());
        student.setGender(studentCreateDto.getGender());
        student.setEnrollmentNo(studentCreateDto.getEnrollmentNo());
        student.setMobileNo(studentCreateDto.getMobileNo());
        return studentRepository.save(student).getStudentToken();
    }
}
