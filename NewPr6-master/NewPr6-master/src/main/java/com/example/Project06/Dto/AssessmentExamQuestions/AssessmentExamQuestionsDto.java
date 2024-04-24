package com.example.Project06.Dto.AssessmentExamQuestions;

import com.example.Project06.Entity.AssessmentExamQuestions;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class AssessmentExamQuestionsDto {
    private Integer AssessmentExamQuestionsID;


    private String question;


    private String questionType;


    private String subject;

    private String level;


    private String option1;


    private String option2;


    private String option3;


    private String option4;

    private String ans;

    public AssessmentExamQuestionsDto() {
    }

    public AssessmentExamQuestionsDto(AssessmentExamQuestions assessmentExamQuestions) {
        AssessmentExamQuestionsID = assessmentExamQuestions.getAssessmentExamQuestionsID();
        this.question = assessmentExamQuestions.getQuestion();
        this.questionType = assessmentExamQuestions.getQuestionType();
        this.subject = assessmentExamQuestions.getSubject();
        this.level = assessmentExamQuestions.getLevel();
        this.option1=assessmentExamQuestions.getOption1();
        this.option2= assessmentExamQuestions.getOption2();
        this.option3=assessmentExamQuestions.getOption3();
        this.option4= assessmentExamQuestions.getOption4();
        this.ans= assessmentExamQuestions.getAns();
    }
}
