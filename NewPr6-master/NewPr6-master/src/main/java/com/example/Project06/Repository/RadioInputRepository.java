package com.example.Project06.Repository;

import com.example.Project06.Entity.RadioInput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RadioInputRepository extends JpaRepository<RadioInput, Integer> {
    Optional<RadioInput> findByuserId(Integer userId);
}