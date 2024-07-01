package com.zippy.users.mappers;

import com.zippy.users.dto.ReferenceDTO;
import com.zippy.users.model.Reference;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = DocumentMapper.class)
public interface ReferenceMapper {
    ReferenceDTO ReferenceToReferenceDTO(Reference reference);

    Reference ReferenceDTOtoReference(ReferenceDTO referenceDTO);
}
