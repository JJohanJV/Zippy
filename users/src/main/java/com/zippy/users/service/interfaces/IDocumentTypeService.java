package com.zippy.users.service.interfaces;

import com.zippy.users.model.DocumentType;

import java.util.List;
import java.util.Optional;

public interface IDocumentTypeService {
    Optional<DocumentType> getDocumentTypeById(Integer id);

    List<DocumentType> getAllDocumentTypes();

    boolean existsDocumentTypeById(Integer id);

}
