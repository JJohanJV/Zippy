package com.zippy.users.repository;

import com.zippy.users.model.PersonalInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonalInformation extends JpaRepository<PersonalInformation, Long> {
}
