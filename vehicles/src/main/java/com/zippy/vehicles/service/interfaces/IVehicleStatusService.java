package com.zippy.vehicles.service.interfaces;

import com.zippy.vehicles.model.VehicleStatus;

import java.util.List;
import java.util.Optional;

public interface IVehicleStatusService {
    Optional<VehicleStatus> findById(Integer id);

    List<VehicleStatus> findAll();

    Boolean existsById(Integer id);
}
