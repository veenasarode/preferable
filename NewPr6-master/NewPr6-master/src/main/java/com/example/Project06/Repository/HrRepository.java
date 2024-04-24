package com.example.Project06.Repository;

import com.example.Project06.Entity.Company;
import com.example.Project06.Entity.Hr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HrRepository extends JpaRepository<Hr, Integer> {

}
