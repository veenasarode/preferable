package com.example.Project06.Repository;

import com.example.Project06.Entity.AssessmentExamQuestions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssessmentExamQuestionsRepo extends JpaRepository<AssessmentExamQuestions, Integer> {
    List<AssessmentExamQuestions> findBySubjectAndLevel(String subject, String level);

    List<AssessmentExamQuestions> findBySubject(String subject);

}
