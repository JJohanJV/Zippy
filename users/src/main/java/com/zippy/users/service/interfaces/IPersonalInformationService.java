package com.zippy.users.service.interfaces;

import com.zippy.users.model.PersonalInformation;

import java.util.List;
import java.util.Optional;

public interface IPersonalInformationService {

    Optional<PersonalInformation> getPersonalInformationById(Long id);

    List<PersonalInformation> getAllPersonalInformation();

    Optional<PersonalInformation> newPersonalInformation(PersonalInformation personalInformation);

    Optional<PersonalInformation> updatePersonalInformation(Long id, PersonalInformation personalInformation);

    void deletePersonalInformation(Long id);
}
