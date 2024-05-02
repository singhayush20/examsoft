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
@Table(name = "question_choice")
public class QuestionChoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "choice_id", nullable = false, unique = true)
    private Long choiceId;

    @Column(name = "question_token", nullable = false, unique = true)
    private String choiceToken;

    @Column(name="sequence_no",nullable = false)
    private Integer sequenceNo;

    @Column(name = "choice_text", nullable = false)
    private String choiceText;

    @Column(name = "is_correct_answer")
    private Boolean isCorrectAnswer;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "question_id", referencedColumnName = "question_id")
    private ExamQuestion question;

    @OneToMany(mappedBy = "selectChoice",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<StudentSelectChoice> studentSelectChoices=new HashSet<>();

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
        this.choiceToken = UUID.randomUUID().toString();
    }


}
