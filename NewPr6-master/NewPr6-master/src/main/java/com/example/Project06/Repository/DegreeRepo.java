package com.example.Project06.Repository;

import com.example.Project06.Entity.Degree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DegreeRepo extends JpaRepository<Degree, Integer> {

    List<Degree> findByuserId(Integer userId);
}
