package com.example.Project06.Repository;

import com.example.Project06.Entity.VideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface VideoEntityRepo extends JpaRepository<VideoEntity,Integer> {

    List<VideoEntity> findByCoursesId(UUID coursesId);

}
