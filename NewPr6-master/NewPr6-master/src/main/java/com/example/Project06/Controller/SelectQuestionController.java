package com.example.Project06.Controller;

import com.example.Project06.Dto.ResponceDto;
import com.example.Project06.Service.QuestionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("selQue")
@RequiredArgsConstructor
@Tag(name = "SELECT_QUESTION_CONTROLLER")
public class SelectQuestionController {

    private final QuestionService questionService;

    @PostMapping("/selectAndSaveRandomQuestions")
    public ResponseEntity<String> selectAndSaveRandomQuestions(@RequestParam Integer userId, @RequestParam String subject, @RequestParam Integer numberOfQuestions, @RequestParam String level ) {
        try {
            questionService.selectAndSaveRandomQuestions(userId, subject, numberOfQuestions, level);
            return new ResponseEntity<>("Questions selected successfully", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Number of requested questions exceeds the total available questions",
                    HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/saveAns")
    public ResponseEntity<?> saveAns(@RequestParam Integer userId, @RequestParam String subject, @RequestParam Integer questionId,@RequestParam String ans) {
        try {
            questionService.saveAns(userId, subject, questionId,ans);

            return new ResponseEntity<>(new ResponceDto("sccuss","answer save successfully"), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new ResponceDto("unsccuss",e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
