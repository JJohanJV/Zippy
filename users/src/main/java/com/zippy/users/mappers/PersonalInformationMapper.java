package com.zippy.users.mappers;

import com.zippy.users.dto.PersonalInformationDTO;
import com.zippy.users.model.PersonalInformation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {DocumentMapper.class, ReferenceMapper.class})
public interface PersonalInformationMapper {
    PersonalInformationDTO PersonalInformatonToPersonalInformationDTO(PersonalInformation personalInformation);

    PersonalInformation PersonalInformatonDTOToPersonalInformation(PersonalInformationDTO personalInformationDTO);
}
