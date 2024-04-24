package com.example.Project06.Service;



import com.example.Project06.Dto.AssessmentExamQuestions.AssessmentExamQuestionsDto;
import com.example.Project06.Dto.AssessmentExamQuestions.AssessmentExamQuestionsDtoQ;
import com.example.Project06.Dto.Plan.PlanDto;

import java.util.List;

public interface AssessmentExamQuestionsService {


    public String AssessmentExamQuestion(AssessmentExamQuestionsDto assessmentExamQuestionsDto);


    public List<AssessmentExamQuestionsDto> getAllAssessmentExamQuestionWithPages(int PageNo);

    public AssessmentExamQuestionsDto findById(Integer AssessmentExamQuestionsID);

    public AssessmentExamQuestionsDtoQ findByIdQ(Integer AssessmentExamQuestionsID);


    public String deletePlan(Integer AssessmentExamQuestionsID);

    public String updateAQFields(AssessmentExamQuestionsDto assessmentExamQuestionsDto,Integer AssessmentExamQuestionsID);


    List<AssessmentExamQuestionsDto> getQBySubject(String subject);

}
