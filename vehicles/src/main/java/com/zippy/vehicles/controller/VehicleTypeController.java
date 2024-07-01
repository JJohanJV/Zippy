package com.zippy.vehicles.controller;

import com.zippy.vehicles.dto.VehicleTypeDTO;
import com.zippy.vehicles.mappers.VehicleTypeMapper;
import com.zippy.vehicles.service.interfaces.IVehicleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicleType")
public class VehicleTypeController {
    private IVehicleTypeService vehicleTypeService;
    private VehicleTypeMapper vehicleTypeMapper;

    @GetMapping("/all")
    public ResponseEntity<List<VehicleTypeDTO>> findAll() {
        return ResponseEntity.ok(vehicleTypeService.findAll().stream().map(vehicleTypeMapper::vehicleTypeToVehicleTypeDTO).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleTypeDTO> findById(@PathVariable Integer id) {
        return vehicleTypeService.findById(id)
                .map(vehicleTypeMapper::vehicleTypeToVehicleTypeDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Autowired
    public void setVehicleTypeService(IVehicleTypeService vehicleTypeService) {
        this.vehicleTypeService = vehicleTypeService;
    }

    @Autowired
    public void setVehicleTypeMapper(VehicleTypeMapper vehicleTypeMapper) {
        this.vehicleTypeMapper = vehicleTypeMapper;
    }
}
