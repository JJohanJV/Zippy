package com.zippy.users.repository;

import com.zippy.users.model.Reference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReferenceRepository extends JpaRepository<Reference, Long> {
}
