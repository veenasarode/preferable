package com.example.Project06.Service;

public interface QuestionService {

    void selectAndSaveRandomQuestions(Integer userId, String subject, Integer numberOfQuestions,  String level);

    void saveAns(Integer userId, String subject, Integer questionId,String ans);
}
