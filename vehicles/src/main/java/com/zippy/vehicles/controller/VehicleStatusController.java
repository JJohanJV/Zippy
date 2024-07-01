package com.zippy.vehicles.controller;

import com.zippy.vehicles.dto.VehicleStatusDTO;
import com.zippy.vehicles.mappers.VehicleStatusMapper;
import com.zippy.vehicles.service.interfaces.IVehicleStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicleStatus")
public class VehicleStatusController {
    private IVehicleStatusService vehicleStatusService;
    private VehicleStatusMapper vehicleStatusMapper;

    @GetMapping("/all")
    public ResponseEntity<List<VehicleStatusDTO>> findAll() {
        return ResponseEntity.ok(vehicleStatusService.findAll().stream().map(vehicleStatusMapper::vehicleStatusToVehicleStatusDTO).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleStatusDTO> findById(@PathVariable Integer id) {
        return vehicleStatusService.findById(id)
                .map(vehicleStatusMapper::vehicleStatusToVehicleStatusDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Autowired
    public void setVehicleStatusService(IVehicleStatusService vehicleStatusService) {
        this.vehicleStatusService = vehicleStatusService;
    }

    @Autowired
    public void setVehicleStatusMapper(VehicleStatusMapper vehicleStatusMapper) {
        this.vehicleStatusMapper = vehicleStatusMapper;
    }
}
