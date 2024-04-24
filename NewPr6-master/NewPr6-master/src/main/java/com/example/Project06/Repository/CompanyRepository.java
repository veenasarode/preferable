package com.example.Project06.Repository;

import com.example.Project06.Entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
    Company findByGestnNo(String gestnNo);
    Company findByRefNo(String refNo);
    @Query("SELECT c FROM Company c WHERE c.userUser.user_id = :userId")
    Optional<Company> findByUserId(@Param("userId") Integer userId);


}
