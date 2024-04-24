package com.example.Project06.Repository;

import com.example.Project06.Entity.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CoursesRepo  extends JpaRepository<Courses, UUID> {
//    Optional<Courses> findById(UUID coursesId);
}
