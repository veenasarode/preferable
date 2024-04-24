package com.example.Project06.Dto;

import com.example.Project06.Entity.Document;
import lombok.Data;

import java.util.List;
@Data
public class ResponseAllDocument {
    private String status;
    private List<Document> response;

    public ResponseAllDocument(String status) {
        this.status = status;
    }
}