package com.example.Project06.Repository;

import com.example.Project06.Entity.SelectQuestionAnsStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SelectQuestionAnsStatusRepo extends JpaRepository<SelectQuestionAnsStatus,Integer> {
    @Query(value = "SELECT * FROM slm_006.select_question_ans_status WHERE ans_status = true AND date_and_time_to_end_exam <= NOW()", nativeQuery = true)
    List<SelectQuestionAnsStatus> findByAnsStatus();
}
