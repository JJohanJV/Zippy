package com.zippy.users.service.impl;

import com.zippy.users.model.Document;
import com.zippy.users.model.Reference;
import com.zippy.users.repository.IReferenceRepository;
import com.zippy.users.service.interfaces.IDocumentService;
import com.zippy.users.service.interfaces.IReferenceService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReferenceServiceImpl implements IReferenceService {
    private IReferenceRepository referenceRepository;
    private IDocumentService documentService;

    private Reference saveReference(Reference reference) {
        return referenceRepository.save(reference);
    }

    @Override
    public Optional<Reference> getReferenceById(Long id) {
        return findReferenceById(id);
    }

    @Override
    public Optional<Reference> newReference(Reference reference) {
        return saveReferenceDocument(reference)
                .map(this::saveReference);
    }

    @Override
    public Optional<Reference> updateReference(Long id, Reference reference) {
        return findReferenceById(id)
                .map(Reference::getId)
                .map(reference::setId)
                .map(this::saveReference);
    }

    private Optional<Reference> findReferenceById(@NotNull Long id) {
        return referenceRepository.findById(id);
    }


    private Optional<Reference> saveReferenceDocument(@NotNull Reference reference) {
        return documentService.newDocument(reference.getDocument())
                .map(Document::getId)
                .map(reference::setDocumentId);
    }

    @Autowired
    public void setReferenceRepository(IReferenceRepository referenceRepository) {
        this.referenceRepository = referenceRepository;
    }

    @Autowired
    public void setDocumentService(IDocumentService documentService) {
        this.documentService = documentService;
    }
}
