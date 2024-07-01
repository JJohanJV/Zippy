package com.zippy.users.mappers;

import com.zippy.users.dto.DocumentTypeDTO;
import com.zippy.users.model.DocumentType;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface DocumentTypeMapper {
    DocumentTypeDTO DocumentTypeToDocumentTypeDTO(DocumentType documentType);

    DocumentType DocumentTypeDTOtoDocumentType(DocumentTypeDTO documentTypeDTO);
}
