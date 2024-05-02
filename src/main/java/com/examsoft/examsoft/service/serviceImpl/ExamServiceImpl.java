package com.examsoft.examsoft.service.serviceImpl;

import com.examsoft.examsoft.model.constants.QuestionType;
import com.examsoft.examsoft.model.dto.exam.ExamCreateDto;
import com.examsoft.examsoft.model.dto.exam.ExamDetailsDto;
import com.examsoft.examsoft.model.dto.examQuestion.ExamQuestionCreateDto;
import com.examsoft.examsoft.model.dto.examQuestion.ExamQuestionDetailsDto;
import com.examsoft.examsoft.model.dto.examQuestion.ExamQuestionListCreateDto;
import com.examsoft.examsoft.model.dto.questionChoice.QuestionChoiceCreateDto;
import com.examsoft.examsoft.model.dto.questionChoice.QuestionChoiceDetailsDto;
import com.examsoft.examsoft.model.dto.studentAnswer.StudentAnswerContentCreateDto;
import com.examsoft.examsoft.model.dto.studentAnswer.StudentAnswerListCreateDto;
import com.examsoft.examsoft.model.entity.*;
import com.examsoft.examsoft.repository.*;
import com.examsoft.examsoft.service.ExamService;
import com.examsoft.examsoft.util.exceptionUtil.ApiException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;

    private final ExamQuestionRepository examQuestionRepository;

    private final QuestionChoiceRepository questionChoiceRepository;

    private final QuestionImageRepository questionImageRepository;

    private final StudentRepository studentRepository;

    private final StudentExamRepository studentExamRepository;

    private final StudentSelectChoiceRepository studentSelectChoiceRepository;

    private final StudentAnswerRepository studentAnswerRepository;
    private final DocumentAnswerRepository documentAnswerRepository;

    @Override
    public String createExam (ExamCreateDto examCreateDto) {
        Exam exam = new Exam();
        exam.setTitle(examCreateDto.getTitle());
        exam.setDescription(examCreateDto.getDescription());
        return examRepository.save(exam).getExamToken();
    }

    @Override
    public Set<String> createExamQuestion (ExamQuestionListCreateDto questions) {
        Exam exam = examRepository.findByExamToken(questions.getExamToken()).orElseThrow(() -> new ApiException("Exam not found"));
        Set<ExamQuestion> questionsList = new HashSet<>();
        int sequenceNo = 1;
        Set<String> tokens = new HashSet<>();
        for (ExamQuestionCreateDto examQuestionCreateDto : questions.getExamQuestions()) {
            ExamQuestion examQuestion = new ExamQuestion();
            examQuestion.setExam(exam); //-set exam
            examQuestion.setSequenceNo(sequenceNo++);//-set sequence no
            examQuestion.setQuestion(examQuestionCreateDto.getQuestion()); //- set question
            examQuestion.setQuestionType(examQuestionCreateDto.getQuestionType()); //- set question type
            ExamQuestion savedExamQuestion = examQuestionRepository.save(examQuestion); //- save the question
            tokens.add(savedExamQuestion.getQuestionToken());
            //-if choices must be saved
            if (examQuestionCreateDto.getQuestionType() == QuestionType.SINGLE_CHOICE || examQuestionCreateDto.getQuestionType() == QuestionType.MULTIPLE_CHOICE) {
                int choiceSequenceNo=1;
                for (QuestionChoiceCreateDto questionChoiceCreateDto : examQuestionCreateDto.getQuestionChoices()) {
                    QuestionChoice questionChoice = new QuestionChoice();
                    questionChoice.setQuestion(savedExamQuestion);
                    questionChoice.setChoiceText(questionChoiceCreateDto.getChoiceText());
                    questionChoice.setIsCorrectAnswer(questionChoiceCreateDto.getIsCorrectAnswer());
                    questionChoice.setSequenceNo(choiceSequenceNo++);
                    questionChoiceRepository.save(questionChoice); //- save each choice
                }
            } else if (examQuestionCreateDto.getQuestionType() == QuestionType.DESCRIPTIVE) {
                if (examQuestionCreateDto.getQuestionChoices() != null && ! examQuestionCreateDto.getQuestionChoices().isEmpty()) {
                    throw new ApiException("Descriptive question should not have choices");
                }
            }
            questionsList.add(examQuestion);
        }
        exam.setExamQuestions(questionsList);
        examRepository.save(exam);
        return tokens;
    }

    @Override
    public List<String> saveExamAnswers (StudentAnswerListCreateDto answers) {
        String studentToken = answers.getStudentToken();
        String examToken = answers.getExamToken();
        List<StudentAnswerContentCreateDto> answerContent = answers.getStudentAnswers(); //-answers
        List<String> answerTokens=new ArrayList<>();
        //-save student, exam relationship
        Student student = this.studentRepository.findByToken(studentToken).orElseThrow(() -> new ApiException("Student not found"));
        Exam exam = this.examRepository.findByExamToken(examToken).orElseThrow(() -> new ApiException("Exam not found"));

        //- save new student exam relationship
        StudentExam studentExam = new StudentExam();
        studentExam.setExam(exam);
        studentExam.setStudent(student);
        studentExamRepository.save(studentExam);

        //-Step 3: Save answers

        for (StudentAnswerContentCreateDto answer : answerContent) {
            StudentAnswer studentAnswer = new StudentAnswer(); //-create new student answer relationship
            //-obtain exam question
            ExamQuestion examQuestion = this.examQuestionRepository.findByQuestionToken(answer.getQuestionToken()).orElseThrow(() -> new ApiException("Question not found"));
            studentAnswer.setQuestion(examQuestion);//-set question
            studentAnswer.setStudent(student);//-set student

            //! The fill type answer content must be set before saving the answer
            //-1. if question is fill type, set answer here itself
            saveFillAnswerContent(answer, examQuestion, studentAnswer);

            //! Answer must be saved before saving other answer contents
            //-2. save the answer to get the required ids, for further relationship
            studentAnswer = studentAnswerRepository.save(studentAnswer);

            //-3. if the question type is single/multi-select
            saveSingleAndMultiSelectChoice(answer, examQuestion, studentAnswer);

            //-4. if the question type is descriptive
            saveDescriptiveContent(answer, examQuestion, studentAnswer);

            //-add token to response
            answerTokens.add(studentAnswer.getStudentAnswerToken());
        }
        return answerTokens;
    }

    @Override
    public ExamDetailsDto examDetails (String examToken) {
        Exam exam=this.examRepository.findByExamToken(examToken).orElseThrow(() -> new ApiException("Exam not found"));
        ExamDetailsDto examDetailsDto=new ExamDetailsDto();
        examDetailsDto.setExamToken(exam.getExamToken());
        examDetailsDto.setTitle(exam.getTitle());
        examDetailsDto.setDescription(exam.getDescription());
        List<ExamQuestion> listExamQuestions=examQuestionRepository.findAllByExamToken(examToken);
        List<ExamQuestionDetailsDto> examQuestionDetailsDtoList=new ArrayList<>();
        for(ExamQuestion examQuestion:listExamQuestions){
            ExamQuestionDetailsDto examQuestionDetailsDto=new ExamQuestionDetailsDto();
            examQuestionDetailsDto.setQuestionToken(examQuestion.getQuestionToken());
            examQuestionDetailsDto.setQuestion(examQuestion.getQuestion());
            examQuestionDetailsDto.setSequenceNo(examQuestion.getSequenceNo());
           if(examQuestion.getHasImages()){
              List<String> imageUrls=questionImageRepository.findAllByQuestionToken(examQuestion.getQuestionToken());
              examQuestionDetailsDto.setImageUrls(imageUrls);
           }
            examQuestionDetailsDto.setQuestionType(examQuestion.getQuestionType());
           if(examQuestion.getQuestionType()==QuestionType.SINGLE_CHOICE || examQuestion.getQuestionType()==QuestionType.MULTIPLE_CHOICE){
               List<QuestionChoice> listQuestionChoices=questionChoiceRepository.findAllByQuestionToken(examQuestion.getQuestionToken());
               List<QuestionChoiceDetailsDto> questionChoiceDetailsDtoList = getQuestionChoices(listQuestionChoices);
               examQuestionDetailsDto.setQuestionChoices(questionChoiceDetailsDtoList);
           }
            examQuestionDetailsDtoList.add(examQuestionDetailsDto);
        }
        examDetailsDto.setQuestions(examQuestionDetailsDtoList);
        return examDetailsDto;
    }

    private static List<QuestionChoiceDetailsDto> getQuestionChoices (List<QuestionChoice> listQuestionChoices) {
        List<QuestionChoiceDetailsDto> questionChoiceDetailsDtoList=new ArrayList<>();
        for(QuestionChoice questionChoice: listQuestionChoices){
            QuestionChoiceDetailsDto questionChoiceDetailsDto=new QuestionChoiceDetailsDto();
            questionChoiceDetailsDto.setToken(questionChoice.getChoiceToken());
            questionChoiceDetailsDto.setChoiceText(questionChoice.getChoiceText());
            questionChoiceDetailsDto.setIsCorrectAnswer(questionChoice.getIsCorrectAnswer());
            questionChoiceDetailsDto.setSequenceNo(questionChoice.getSequenceNo());
            questionChoiceDetailsDtoList.add(questionChoiceDetailsDto);
        }
        return questionChoiceDetailsDtoList;
    }

    private static void saveFillAnswerContent (StudentAnswerContentCreateDto answer, ExamQuestion examQuestion, StudentAnswer studentAnswer) {
        if (examQuestion.getQuestionType() == QuestionType.FILL) {
            studentAnswer.setFillAnswer(answer.getFillAnswer());
        }
    }

    private void saveSingleAndMultiSelectChoice (StudentAnswerContentCreateDto answer, ExamQuestion examQuestion, StudentAnswer studentAnswer) {
        if (examQuestion.getQuestionType() == QuestionType.SINGLE_CHOICE || examQuestion.getQuestionType() == QuestionType.MULTIPLE_CHOICE) {
            List<String> selectedChoices = answer.getChoiceTokens(); //-obtain the list of selected choices
            //-ensure that single choice question has only 1 choice
            if (selectedChoices.size() > 1 && examQuestion.getQuestionType() == QuestionType.SINGLE_CHOICE) {
                throw new ApiException("Single choice question should not have more than one choice");
            }
            //-create question choice and answer relations
            for (String choiceToken : selectedChoices) {
                QuestionChoice questionChoice =
                        this.questionChoiceRepository.findByChoiceTokenAndQuestionToken(choiceToken, answer.getQuestionToken()).orElseThrow(() -> new ApiException("Choice " + "not " + "found")); //-obtain question choice
                StudentSelectChoice studentSelectChoice = new StudentSelectChoice();//-create new answer, option relationship (which answer and what choices are selected)
                studentSelectChoice.setSelectChoice(questionChoice);
                studentSelectChoice.setStudentAnswer(studentAnswer);
                studentSelectChoiceRepository.save(studentSelectChoice); //-save the relationship
            }
        }
    }

    private void saveDescriptiveContent (StudentAnswerContentCreateDto answer, ExamQuestion examQuestion, StudentAnswer studentAnswer) {
        if (examQuestion.getQuestionType() == QuestionType.DESCRIPTIVE) {
            DocumentAnswer docAnswer = new DocumentAnswer();
            docAnswer.setAnswer(answer.getDescriptiveAnswer().toString());
            docAnswer.setStudentAnswer(studentAnswer);
            documentAnswerRepository.save(docAnswer);
        }
    }


}
