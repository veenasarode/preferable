package com.example.Project06.Entity;

import com.example.Project06.Dto.AssessmentExamQuestions.AssessmentExamQuestionsDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@Table(name = "AssessmentExamQuestions")
public class AssessmentExamQuestions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AssessmentExamQuestionsID", nullable = false)
    private Integer AssessmentExamQuestionsID;
    @Lob
    @Column(name = "Question", length = 250)
    private String question;

    @Column(name = "QuestionType")
    private String questionType;

    @Column(name = "Subject")
    private String subject;

    @Column(name = "Level")
    private String level;

    @Column(name = "Option1")
    private String option1;

    @Column(name = "Option2")
    private String option2;

    @Column(name = "Option3")
    private String option3;

    @Column(name = "Option4")
    private String option4;

    @Column(name = "Ans")
    private String ans;


    public AssessmentExamQuestions() {
    }

    public AssessmentExamQuestions(AssessmentExamQuestionsDto assessmentExamQuestionsDto) {
        AssessmentExamQuestionsID = assessmentExamQuestionsDto.getAssessmentExamQuestionsID();
        this.question = assessmentExamQuestionsDto.getQuestion();
        this.questionType = assessmentExamQuestionsDto.getQuestionType();
        this.subject = assessmentExamQuestionsDto.getSubject();
        this.level = assessmentExamQuestionsDto.getLevel();
        this.option1= assessmentExamQuestionsDto.getOption1();
        this.option2= assessmentExamQuestionsDto.getOption2();
        this.option3= assessmentExamQuestionsDto.getOption3();
        this.option4 = assessmentExamQuestionsDto.getOption4();
        this.ans = assessmentExamQuestionsDto.getAns();
    }
}
