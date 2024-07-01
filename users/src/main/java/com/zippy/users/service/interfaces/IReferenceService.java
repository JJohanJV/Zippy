package com.zippy.users.service.interfaces;

import com.zippy.users.model.Reference;

import java.util.Optional;

public interface IReferenceService {

    Optional<Reference> newReference(Reference reference);

    Optional<Reference> updateReference(Long id, Reference reference);

    Optional<Reference> getReferenceById(Long id);

}
