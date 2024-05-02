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

import java.sql.Date;
import java.util.UUID;

@Table(name="document_answer")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocumentAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="document_answer_id")
    private Long documentAnswerId;

    @Column(name="document_answer_token",nullable = false,unique = true)
    private String documentAnswerToken;

    @Column(name = "answer",nullable = false,length = 2000)
    private String answer;

    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "student_answer_id",referencedColumnName = "student_answer_id")
    private StudentAnswer studentAnswer;

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
        this.documentAnswerToken= UUID.randomUUID().toString();
    }
}
