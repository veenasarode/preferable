package com.example.Project06.Service;

import com.example.Project06.Dto.DocumentDto;
import com.example.Project06.Entity.Document;

import java.util.List;

public interface IDocument {
    public String addDocument(DocumentDto documentDto);

    public List<Document> getAllDocument(Integer userId, String DocumentType);

    public List<Document> getByUserId(Integer userId);
}
