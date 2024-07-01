package com.zippy.vehicles.repository;

import com.zippy.vehicles.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByStationId(Long stationId);
    List<Vehicle> findByVehicleStatusId(int id);
    List<Vehicle> findByStationIdAndVehicleStatusId(Long stationId, int vehicleStatusId);
}
