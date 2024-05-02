package com.examsoft.examsoft.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student_question_answer")
public class StudentAnswer {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="student_answer_id")
    private Long studentAnswerId;

    @Column(name = "student_answer_token",nullable = false,unique = true)
    private String studentAnswerToken;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "student_id",referencedColumnName = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "question_id",referencedColumnName = "question_id")
    private ExamQuestion question;

    @OneToMany(mappedBy = "studentAnswer",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<DocumentAnswer> documentAnswers=new HashSet<>();

    @OneToMany(mappedBy = "studentAnswer",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<StudentSelectChoice> selectedChoices=new HashSet<>();

    @Column(name = "fill_answer")
    private String fillAnswer;

    @CreatedDate
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    @PrePersist
    public void onCreate(){
        this.studentAnswerToken = UUID.randomUUID().toString();
    }
}
