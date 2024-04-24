package com.example.Project06.Repository;


import com.example.Project06.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PaymentRepo extends JpaRepository<Payment,Integer> {
    public Payment findByOrderId (String orderId);

    @Query("SELECT p FROM Payment p WHERE p.user.user_id = :user_id")
    List<Payment> findByUserId(@Param("user_id") Integer user_id);

}
