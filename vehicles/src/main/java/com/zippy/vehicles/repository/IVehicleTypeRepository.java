package com.zippy.vehicles.repository;

import com.zippy.vehicles.model.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IVehicleTypeRepository extends JpaRepository<VehicleType, Integer> {
}
