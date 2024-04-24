package com.example.Project06.Repository;

import com.example.Project06.Entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CertificateRepo extends JpaRepository <Certificate, Integer> {
    List<Certificate> findByuserId(Integer userId);
}
