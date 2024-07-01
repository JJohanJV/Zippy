package com.zippy.users.service.interfaces;

import com.zippy.users.model.Document;

import java.util.Optional;

public interface IDocumentService {

    Optional<Document> UpdateDocument(Long id, Document document);

    Optional<Document> getDocumentById(Long id);

    void deleteDocument(Long id);

    Optional<Document> updateDocumentType(Long id, Integer typeId);

    Optional<Document> newDocument(Document document);
}
