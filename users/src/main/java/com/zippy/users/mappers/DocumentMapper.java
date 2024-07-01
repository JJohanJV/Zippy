package com.zippy.users.mappers;

import com.zippy.users.dto.DocumentDTO;
import com.zippy.users.model.Document;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = DocumentTypeMapper.class)
public interface DocumentMapper {
    DocumentDTO DocumentToDocumentDTO(Document document);

    @Mapping(source = "type.id", target = "typeId")
    Document DocumentDTOtoDocument(DocumentDTO documentDTO);
}
