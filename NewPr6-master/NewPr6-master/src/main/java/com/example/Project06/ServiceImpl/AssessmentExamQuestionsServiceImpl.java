package com.example.Project06.ServiceImpl;

import com.example.Project06.Dto.AssessmentExamQuestions.AssessmentExamQuestionsDto;
import com.example.Project06.Dto.AssessmentExamQuestions.AssessmentExamQuestionsDtoQ;
import com.example.Project06.Entity.AssessmentExamQuestions;
import com.example.Project06.Entity.Plan;
import com.example.Project06.Repository.AssessmentExamQuestionsRepo;
import com.example.Project06.Service.AssessmentExamQuestionsService;
import com.example.Project06.exception.AssessmentExamQuestionsException;
import com.example.Project06.exception.PageNotFoundException;
import com.example.Project06.exception.PlanNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AssessmentExamQuestionsServiceImpl implements AssessmentExamQuestionsService {

    private final AssessmentExamQuestionsRepo assessmentExamQuestionsRepo;
    @Override
    public String AssessmentExamQuestion(AssessmentExamQuestionsDto assessmentExamQuestionsDto) {
        AssessmentExamQuestions assessmentExamQuestions=new AssessmentExamQuestions(assessmentExamQuestionsDto);
        assessmentExamQuestionsRepo.save(assessmentExamQuestions);
        return "Add AssessmentExamQuestion ";

    }



    @Override
    public List<AssessmentExamQuestionsDto> getAllAssessmentExamQuestionWithPages(int PageNo) {
        List<AssessmentExamQuestions> listOfAllAssessmentExamQuestion= assessmentExamQuestionsRepo.findAll();
        if ((PageNo * 10) > listOfAllAssessmentExamQuestion.size() - 1) {
            throw new PageNotFoundException("page not found");
        }
        if (listOfAllAssessmentExamQuestion.size() <= 0) {
            throw new AssessmentExamQuestionsException("Assessment Exam Question not found", HttpStatus.NOT_FOUND);
        }
        List<AssessmentExamQuestionsDto> assessmentExamQuestionsDtoList = new ArrayList<>();


        int pageStart = PageNo * 10;
        int pageEnd = pageStart + 10;
        int diff = (listOfAllAssessmentExamQuestion.size()) - pageStart;
        for (int counter = pageStart, i = 1; counter < pageEnd; counter++, i++) {
            if (pageStart > listOfAllAssessmentExamQuestion.size()) {
                break;
            }
            AssessmentExamQuestionsDto assessmentExamQuestionsDto= new AssessmentExamQuestionsDto(listOfAllAssessmentExamQuestion.get(counter));
            assessmentExamQuestionsDto.setAssessmentExamQuestionsID(listOfAllAssessmentExamQuestion.get(counter).getAssessmentExamQuestionsID());
            assessmentExamQuestionsDtoList.add(assessmentExamQuestionsDto);
            if (diff == i) {
                break;
            }
        }
        return assessmentExamQuestionsDtoList;
    }


    @Override
    public AssessmentExamQuestionsDto findById(Integer AssessmentExamQuestionsID) {

        Optional<AssessmentExamQuestions> assessmentExamQuestions = assessmentExamQuestionsRepo.findById(AssessmentExamQuestionsID);
        if (assessmentExamQuestions.isEmpty()){
            throw new AssessmentExamQuestionsException("assessment Exam Questions not found",HttpStatus.NOT_FOUND);

        }
        AssessmentExamQuestionsDto assessmentExamQuestionsDto = new AssessmentExamQuestionsDto(assessmentExamQuestions.get());
        assessmentExamQuestionsDto.setAssessmentExamQuestionsID(AssessmentExamQuestionsID);
        return assessmentExamQuestionsDto;

    }

    @Override
    public AssessmentExamQuestionsDtoQ findByIdQ(Integer AssessmentExamQuestionsID) {

        Optional<AssessmentExamQuestions> assessmentExamQuestions = assessmentExamQuestionsRepo.findById(AssessmentExamQuestionsID);
        if (assessmentExamQuestions.isEmpty()){
            throw new AssessmentExamQuestionsException("assessment Exam Questions not found",HttpStatus.NOT_FOUND);

        }
        AssessmentExamQuestionsDtoQ assessmentExamQuestionsDto = new AssessmentExamQuestionsDtoQ(assessmentExamQuestions.get());
        assessmentExamQuestionsDto.setAssessmentExamQuestionsID(AssessmentExamQuestionsID);
        return assessmentExamQuestionsDto;

    }

    @Override
    public String deletePlan(Integer AssessmentExamQuestionsID) {
        AssessmentExamQuestionsDto exit001= findById(AssessmentExamQuestionsID);
        if (exit001== null){
            throw  new AssessmentExamQuestionsException("assessmentExamQuestions not found");
        }
        assessmentExamQuestionsRepo.deleteById(AssessmentExamQuestionsID);
         return AssessmentExamQuestionsID +"assessment Exam Questions has been deleted successfully";

    }

    @Override
    public String updateAQFields(AssessmentExamQuestionsDto assessmentExamQuestionsDto, Integer AssessmentExamQuestionsID) {
        AssessmentExamQuestions assessmentExamQuestions = assessmentExamQuestionsRepo.findById(AssessmentExamQuestionsID)
                .orElseThrow(() -> new AssessmentExamQuestionsException("AssessmentExamQuestions not found", HttpStatus.NOT_FOUND));

        if (assessmentExamQuestionsDto.getQuestion() != null) {
            assessmentExamQuestions.setQuestion(assessmentExamQuestionsDto.getQuestion());
        }

        if (assessmentExamQuestionsDto.getQuestionType() != null) {
            assessmentExamQuestions.setQuestionType(assessmentExamQuestionsDto.getQuestionType());
        }

        if (assessmentExamQuestionsDto.getSubject() != null) {
            assessmentExamQuestions.setSubject(assessmentExamQuestionsDto.getSubject());
        }

        if (assessmentExamQuestionsDto.getLevel() != null) {
            assessmentExamQuestions.setLevel(assessmentExamQuestionsDto.getLevel());
        }

        if (assessmentExamQuestionsDto.getOption1() != null) {
            assessmentExamQuestions.setOption1(assessmentExamQuestionsDto.getOption1());
        }

        if (assessmentExamQuestionsDto.getOption2() != null) {
            assessmentExamQuestions.setOption2(assessmentExamQuestionsDto.getOption2());
        }

        if (assessmentExamQuestionsDto.getOption3() != null) {
            assessmentExamQuestions.setOption3(assessmentExamQuestionsDto.getOption3());
        }

        if (assessmentExamQuestionsDto.getOption4() != null) {
            assessmentExamQuestions.setOption4(assessmentExamQuestionsDto.getOption4());
        }

        if (assessmentExamQuestionsDto.getAns() != null) {
            assessmentExamQuestions.setAns(assessmentExamQuestionsDto.getAns());
        }
        assessmentExamQuestionsRepo.save(assessmentExamQuestions);

        return "AssessmentExamQuestions Updated: " + AssessmentExamQuestionsID;
    }

    @Override
    public List<AssessmentExamQuestionsDto> getQBySubject(String subject) {
        List<AssessmentExamQuestions> questionsList = assessmentExamQuestionsRepo.findBySubject(subject);

        if (questionsList.isEmpty()) {
            throw new AssessmentExamQuestionsException("No Assessment Exam Questions found for subject: " + subject, HttpStatus.NOT_FOUND);
        }

        List<AssessmentExamQuestionsDto> questionsDtoList = new ArrayList<>();
        for (AssessmentExamQuestions question : questionsList) {
            AssessmentExamQuestionsDto assessmentExamQuestionsDto = new AssessmentExamQuestionsDto(question);
            assessmentExamQuestionsDto.setAssessmentExamQuestionsID(question.getAssessmentExamQuestionsID());
            questionsDtoList.add(assessmentExamQuestionsDto);
        }

        return questionsDtoList;
    }
}
