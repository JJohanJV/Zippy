package com.zippy.users.service.impl;

import com.zippy.users.exceptions.PersonalInformationCreationException;
import com.zippy.users.model.Document;
import com.zippy.users.model.PersonalInformation;
import com.zippy.users.model.Reference;
import com.zippy.users.repository.IPersonalInformation;
import com.zippy.users.service.interfaces.IDocumentService;
import com.zippy.users.service.interfaces.IPersonalInformationService;
import com.zippy.users.service.interfaces.IReferenceService;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonalInformationServiceImpl implements IPersonalInformationService {
    private IPersonalInformation personalInformationRepository;
    private IDocumentService documentService;
    private IReferenceService referenceService;

    private PersonalInformation savePersonalInformation(PersonalInformation personalInformation) {
        return personalInformationRepository.save(personalInformation);
    }

    @Override
    public Optional<PersonalInformation> getPersonalInformationById(Long id) {
        return personalInformationRepository.findById(id);
    }

    @Override
    public List<PersonalInformation> getAllPersonalInformation() {
        return personalInformationRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<PersonalInformation> updatePersonalInformation(Long id, PersonalInformation personalInformation) {
        return getPersonalInformationById(id)
                .map(PersonalInformation::getId)
                .map(personalInformation::setId)
                .map(this::savePersonalInformation);
    }

    @Override
    public void deletePersonalInformation(Long id) {
        personalInformationRepository.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = PersonalInformationCreationException.class)
    public Optional<PersonalInformation> newPersonalInformation(@NotNull PersonalInformation personalInformation) throws PersonalInformationCreationException {
        Optional<PersonalInformation> result = saveNewDocumentFromPersonalInformation(personalInformation)
                .flatMap(this::saveNewReferenceFromPersonalInformation)
                .map(this::savePersonalInformation);
        if (result.isEmpty()) {
            throw new PersonalInformationCreationException("Error creating new personal information.");
        }
        return result;
    }

    private Optional<PersonalInformation> saveNewDocumentFromPersonalInformation(@NotNull PersonalInformation personalInformation) {
        return documentService.newDocument(personalInformation.getDocument())
                .map(Document::getId)
                .map(personalInformation::setDocumentId);
    }

    private Optional<PersonalInformation> saveNewReferenceFromPersonalInformation(@NotNull PersonalInformation personalInformation) {
        return referenceService.newReference(personalInformation.getReference())
                .map(Reference::getId)
                .map(personalInformation::setReferenceId);
    }


    @Autowired
    public void setPersonalInformationRepository(IPersonalInformation personalInformationRepository) {
        this.personalInformationRepository = personalInformationRepository;
    }

    @Autowired
    public void setDocumentService(IDocumentService documentService) {
        this.documentService = documentService;
    }

    @Autowired
    public void setReferenceService(IReferenceService referenceService) {
        this.referenceService = referenceService;
    }
}
