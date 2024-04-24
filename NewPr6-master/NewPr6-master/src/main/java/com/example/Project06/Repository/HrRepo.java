package com.example.Project06.Repository;

import com.example.Project06.Entity.Hr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface HrRepo extends JpaRepository<Hr,Integer> {
    List<Hr> findByRefNoOfCompany(String refNoOfCompany);
}
