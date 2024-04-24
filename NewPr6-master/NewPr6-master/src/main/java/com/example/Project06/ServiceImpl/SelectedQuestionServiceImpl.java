package com.example.Project06.ServiceImpl;

import com.example.Project06.Entity.AssessmentExamQuestions;
import com.example.Project06.Entity.SelectQuestionAnsStatus;
import com.example.Project06.Entity.SelectedQuestions;
import com.example.Project06.Repository.AssessmentExamQuestionsRepo;
import com.example.Project06.Repository.SelectQuestionAnsStatusRepo;
import com.example.Project06.Repository.SelectedQuestionsRepository;
import com.example.Project06.Repository.UserRepository;
import com.example.Project06.Service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class SelectedQuestionServiceImpl implements QuestionService {

    private final AssessmentExamQuestionsRepo assessmentExamQuestionsRepo;

    private final SelectedQuestionsRepository selectedQuestionsRepository;
    private final SelectQuestionAnsStatusRepo selectQuestionAnsStatusRepo;

    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(SelectedQuestionServiceImpl.class);

    @Override
    public void selectAndSaveRandomQuestions(Integer userId, String subject, Integer numberOfQuestions,  String level)
            throws IllegalArgumentException {
        try {
            List<AssessmentExamQuestions> allQuestions = assessmentExamQuestionsRepo.findBySubjectAndLevel(subject, level);  // Updated method

            int totalQuestions = allQuestions.size();

            if (numberOfQuestions > totalQuestions) {
                throw new IllegalArgumentException("Number of requested questions exceeds the total available questions. Please choose a smaller number.");
            }

            int questionsToSelect = Math.min(totalQuestions, numberOfQuestions);

            Random random = new Random();

            List<AssessmentExamQuestions> filteredQuestions = new ArrayList<>();

            for (AssessmentExamQuestions question : allQuestions) {
                if (question.getLevel().equals(level)) {
                    filteredQuestions.add(question);
                }
            }

            List<AssessmentExamQuestions> shuffledQuestions = new ArrayList<>(filteredQuestions);
            for (int i = shuffledQuestions.size() - 1; i > 0; i--) {
                int index = random.nextInt(i + 1);
                AssessmentExamQuestions temp = shuffledQuestions.get(index);
                shuffledQuestions.set(index, shuffledQuestions.get(i));
                shuffledQuestions.set(i, temp);
            }

            for (int i = 0; i < questionsToSelect; i++) {
                AssessmentExamQuestions selectedQuestion = shuffledQuestions.get(i);

                SelectedQuestions newSelectedQuestion = new SelectedQuestions();
                newSelectedQuestion.setUserId(userId);
                newSelectedQuestion.setSubject(subject);
                newSelectedQuestion.setQuestionId(selectedQuestion.getAssessmentExamQuestionsID());
                selectedQuestionsRepository.save(newSelectedQuestion);
            }

            logger.info("Questions selected successfully");
        } catch (IllegalArgumentException e) {
            logger.error("IllegalArgumentException: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected Exception: {}", e.getMessage());
            throw new RuntimeException("Something went wrong. Please try again later.", e);
        }
    }

    @Override
    public void saveAns(Integer userId, String subject, Integer questionId,String ans) {
       SelectedQuestions selectedQuestions=selectedQuestionsRepository.findByEntitys(userId,subject,questionId).orElseThrow(()->new RuntimeException("selected questions are not found by entitys"));
       if (selectedQuestions.getAnswer()!=null)throw new RuntimeException("ans is allready present for this question");
       List<Object> selectedQuestionsList = selectedQuestionsRepository.findByAns(selectedQuestions.getUserId());

       if (selectedQuestionsList.size() == 0){
           SelectQuestionAnsStatus selectQuestionAnsStatus =SelectQuestionAnsStatus
                   .builder()
                   .ansStatus(true)
                   .dateTimeExamStart(LocalDateTime.now())
                   .dateAndTimeToEndExam(LocalDateTime.now().plusHours(1))
                   .subject(selectedQuestions.getSubject())
                   .userId(selectedQuestions.getUserId())
                   .build();
           selectQuestionAnsStatusRepo.save(selectQuestionAnsStatus);
       }
       selectedQuestions.setAnswer(ans);
        selectedQuestionsRepository.save(selectedQuestions);

    }
}
