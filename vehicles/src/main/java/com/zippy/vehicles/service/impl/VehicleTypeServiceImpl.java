package com.zippy.vehicles.service.impl;

import com.zippy.vehicles.model.VehicleType;
import com.zippy.vehicles.repository.IVehicleTypeRepository;
import com.zippy.vehicles.service.interfaces.IVehicleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleTypeServiceImpl implements IVehicleTypeService {
    private IVehicleTypeRepository vehicleTypeRepository;

    @Override
    public Optional<VehicleType> findById(Integer id) {
        return vehicleTypeRepository.findById(id);
    }

    @Override
    public List<VehicleType> findAll() {
        return vehicleTypeRepository.findAll();
    }


    @Autowired
    public void setVehicleTypeRepository(IVehicleTypeRepository vehicleTypeRepository) {
        this.vehicleTypeRepository = vehicleTypeRepository;
    }

}
