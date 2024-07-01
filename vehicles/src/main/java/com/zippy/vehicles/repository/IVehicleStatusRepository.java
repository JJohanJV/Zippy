package com.zippy.vehicles.repository;

import com.zippy.vehicles.model.VehicleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IVehicleStatusRepository extends JpaRepository<VehicleStatus, Integer> {
}
