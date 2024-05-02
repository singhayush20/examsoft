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

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="question_image")
public class QuestionImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="question_image_id")
    private Long questionImageId;

    @Column(name="image_token",nullable = false,unique = true)
    private String imageToken;

    @Column(name="image_url",nullable = false,length = 500)
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name="question_id",referencedColumnName = "question_id")
    private ExamQuestion question;

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
        this.imageToken = UUID.randomUUID().toString();
    }

}
