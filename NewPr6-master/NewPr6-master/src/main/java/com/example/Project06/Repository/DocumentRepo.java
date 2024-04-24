package com.example.Project06.Repository;

import com.example.Project06.Entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepo extends JpaRepository<Document,Integer> {
    @Query("SELECT jfq FROM Document jfq WHERE jfq.userId = :userId AND jfq.documentType = :documentType")
    public List<Document> findByDocumentTypeAndUserID(Integer userId, String documentType);
    @Query("SELECT jfq FROM Document jfq WHERE jfq.userId = :userId")
    public List<Document> findByUserId(Integer userId);
}