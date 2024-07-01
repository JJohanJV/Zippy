package com.zippy.vehicles.service.impl;

import com.zippy.vehicles.model.VehicleStatus;
import com.zippy.vehicles.repository.IVehicleStatusRepository;
import com.zippy.vehicles.service.interfaces.IVehicleStatusService;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleStatusServiceImpl implements IVehicleStatusService {
    private IVehicleStatusRepository vehicleStatusRepository;

    @Override
    public Optional<VehicleStatus> findById(@Nonnull Integer id) {
        return vehicleStatusRepository.findById(id);
    }

    @Override
    public List<VehicleStatus> findAll() {
        return vehicleStatusRepository.findAll();
    }

    @Override
    public Boolean existsById(Integer id) {
        return vehicleStatusRepository.existsById(id);
    }

    @Autowired
    public void setVehicleStatusRepository(IVehicleStatusRepository vehicleStatusRepository) {
        this.vehicleStatusRepository = vehicleStatusRepository;
    }
}
