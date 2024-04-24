package com.example.Project06.Repository;

import com.example.Project06.Entity.SelectedQuestions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SelectedQuestionsRepository extends JpaRepository<SelectedQuestions, Integer> {

    @Query(value = "SELECT * FROM slm_006.selected_questions " +
            "WHERE question_id = :questionId AND subject = :subject AND user_id = :userId", nativeQuery = true)
    Optional<SelectedQuestions> findByEntitys(@Param("userId") Integer userId,
                                             @Param("subject") String subject,
                                             @Param("questionId") Integer questionId);

    @Query(value = "SELECT answer FROM slm_006.selected_questions WHERE answer IS NOT NULL AND user_id = :userId ", nativeQuery = true)
    List<Object> findByAns(@Param("userId") Integer userId);
}
