package com.example.Project06.Repository;

import com.example.Project06.Entity.StudentProfile;
import com.example.Project06.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentProfRepo extends JpaRepository <StudentProfile, Integer> {
    Optional<StudentProfile> findByUserUser(User user);

    @Query("SELECT sp FROM StudentProfile sp WHERE sp.userUser.user_id = :userId")
    Optional<StudentProfile> findByUserUserId(@Param("userId") Integer userId);

}


