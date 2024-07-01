package com.zippy.vehicles.service.interfaces;

import com.zippy.vehicles.model.VehicleType;

import java.util.List;
import java.util.Optional;

public interface IVehicleTypeService {
    Optional<VehicleType> findById(Integer id);

    List<VehicleType> findAll();
}
