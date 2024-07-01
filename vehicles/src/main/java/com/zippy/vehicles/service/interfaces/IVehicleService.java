package com.zippy.vehicles.service.interfaces;

import com.zippy.vehicles.model.Vehicle;

import java.util.List;
import java.util.Optional;

public interface IVehicleService {
    List<Vehicle> findAll();

    Optional<Vehicle> findById(Long id);

    List<Vehicle> findByStationId(Long stationId);

    List<Vehicle> findByVehicleStatusId(int id);

    List<Vehicle> findByStationIdAndVehicleStatusId(Long stationId, int vehicleStatusId);

    Optional<Vehicle> updateVehicleStatusId(Vehicle vehicle, Integer statusId);

    Vehicle updateVehicleStationId(Vehicle vehicle, Long stationId);
}
