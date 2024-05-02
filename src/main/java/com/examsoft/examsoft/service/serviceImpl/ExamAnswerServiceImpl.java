package com.examsoft.examsoft.service.serviceImpl;

import com.examsoft.examsoft.model.dto.studentAnswer.StudentAnswerContentDetailsDto;
import com.examsoft.examsoft.model.dto.studentAnswer.StudentAnswerDetailsDto;
import com.examsoft.examsoft.model.entity.ExamQuestion;
import com.examsoft.examsoft.model.entity.StudentAnswer;
import com.examsoft.examsoft.repository.StudentAnswerRepository;
import com.examsoft.examsoft.service.ExamAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamAnswerServiceImpl implements ExamAnswerService {

    private final StudentAnswerRepository studentAnswerRepository;

    @Override
    public List<StudentAnswerDetailsDto> getAnswersForExamAndStudent (String examToken, String studentToken) {
        // List<StudentAnswer> studentAnswers=this.studentAnswerRepository.findAllByStudentAndExamToken(studentToken,examToken);
        // StudentAnswerDetailsDto studentAnswerDetailsDto=new StudentAnswerDetailsDto();
        // studentAnswerDetailsDto.setStudentToken(studentToken);
        // studentAnswerDetailsDto.setExamToken(examToken);
        // for(StudentAnswer studentAnswer: studentAnswers){
        //     ExamQuestion question=studentAnswer.getQuestion();
        //
        //
        // }
        return null;
    }
}
