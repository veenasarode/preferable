package com.example.Project06.Repository;

import com.example.Project06.Entity.Tpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TpoRepo extends JpaRepository<Tpo, Integer> {
}
