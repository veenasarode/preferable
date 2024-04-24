package com.example.Project06.Repository;

import com.example.Project06.Entity.Job;
import com.example.Project06.Entity.StudentApplication;
import com.example.Project06.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer>, JpaSpecificationExecutor<Job> {


    Job findByJobId(Integer jobId);

    public List<Job> getJobsByStatus(String status);


    List<Job> findByUserUser(User user);



    List<Job> findByRefNo(String refNo);



    @Query("SELECT COUNT(j) FROM Job j WHERE j.userUser.id = :userId")
    Long countByUserId(@Param("userId") Integer userId);


}

