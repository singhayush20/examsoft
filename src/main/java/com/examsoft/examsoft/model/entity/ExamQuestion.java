package com.examsoft.examsoft.model.entity;

import com.examsoft.examsoft.model.constants.QuestionType;
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

@Entity
@Table(name="exam_question")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExamQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="question_id",nullable = false,unique = true)
    private Long questionId;

    @Column(name="question_token",nullable = false,unique = true)
    private String questionToken;

    @Column(name="question",nullable = false)
    private String question;

    @Column(name="sequenceNo",nullable = false)
    private Integer sequenceNo;

    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.DETACH})
    @JoinColumn(name="exam_id",referencedColumnName = "exam_id")
    private Exam exam;

    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<QuestionImage> questionImages=new HashSet<>();

    @Column(name="has_images",columnDefinition = "boolean default false")
    private Boolean hasImages; //flag used internally to optimize fetching

    @Column(name="question_type",nullable = false)
    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<QuestionChoice> questionChoices=new HashSet<>();

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
        this.questionToken= UUID.randomUUID().toString();
        this.hasImages=false; //default
    }
}
