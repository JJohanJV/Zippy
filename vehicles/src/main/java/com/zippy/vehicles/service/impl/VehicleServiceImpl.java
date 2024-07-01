package com.zippy.vehicles.service.impl;

import com.zippy.vehicles.model.Vehicle;
import com.zippy.vehicles.repository.IVehicleRepository;
import com.zippy.vehicles.service.interfaces.IVehicleService;
import com.zippy.vehicles.service.interfaces.IVehicleStatusService;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements IVehicleService {
    private IVehicleRepository vehicleRepository;
    private IVehicleStatusService vehicleStatusService;

    @Override
    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    @Override
    public Optional<Vehicle> findById(@Nonnull Long id) {
        return vehicleRepository.findById(id);
    }

    @Override
    public List<Vehicle> findByStationId(Long stationId) {
        return vehicleRepository.findByStationId(stationId);
    }

    @Override
    public List<Vehicle> findByStationIdAndVehicleStatusId(Long stationId, int vehicleStatusId) {
        return vehicleRepository.findByStationIdAndVehicleStatusId(stationId, vehicleStatusId);
    }


    @Override
    public List<Vehicle> findByVehicleStatusId(int id) {
        return vehicleRepository.findByVehicleStatusId(id);
    }

    @Override
    public Optional<Vehicle> updateVehicleStatusId(Vehicle vehicle, Integer statusId) {
        return vehicleStatusExists(statusId)
                .filter(Boolean::booleanValue)
                .map(exists -> vehicle.setVehicleStatusId(statusId))
                .map(this::save);
    }

    @Override
    public Vehicle updateVehicleStationId(Vehicle vehicle, Long stationId) {
        return save(vehicle.setStationId(stationId));
    }


    private Vehicle save(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    private Optional<Boolean> vehicleStatusExists(Integer statusId) {
        return Optional.of(vehicleStatusService.existsById(statusId));
    }

    @Autowired
    public void setVehicleRepository(IVehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Autowired
    public void setVehicleStatusService(IVehicleStatusService vehicleStatusService) {
        this.vehicleStatusService = vehicleStatusService;
    }
}
