package com.example.Project06.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SelectQuestionAnsStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "selectQuestionAnsStatusId", nullable = false)
    private Integer selectedQuestionId;

    @Column(name = "userId")
    private Integer userId;

    @Column(name = "subject")
    private String subject;

    @Column(name = "ansStatus")
    private Boolean ansStatus;

    @Column(name = "dateAndTimeToStartExam")
    private LocalDateTime dateTimeExamStart;

    @Column(name = "dateAndTimeToEndExam")
    private LocalDateTime dateAndTimeToEndExam;


}
