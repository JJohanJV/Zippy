package com.zippy.vehicles.controller;


import com.zippy.vehicles.dto.*;
import com.zippy.vehicles.mappers.*;
import com.zippy.vehicles.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/vehicles")
public class VehicleController {
    private IVehicleService vehicleService;
    private VehicleMapper vehicleMapper;


    @GetMapping("/all")
    public ResponseEntity<List<VehicleDTO>> findAll() {
        return ResponseEntity
                .ok(vehicleService.findAll().stream().map(vehicleMapper::vehicleToVehicleDTO).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDTO> findById(@PathVariable Long id) {
        return vehicleService.findById(id)
                .map(vehicleMapper::vehicleToVehicleDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{vehicleId}/status/{statusId}")
    public ResponseEntity<VehicleDTO> updateVehicleStatus(@PathVariable Long vehicleId, @PathVariable Integer statusId) {
        return vehicleService.findById(vehicleId)
                .flatMap(vehicle -> vehicleService.updateVehicleStatusId(vehicle, statusId))
                .map(vehicleMapper::vehicleToVehicleDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/station/{stationId}")
    public ResponseEntity<VehicleDTO> updateVehicleStatus(@PathVariable Long id, @PathVariable Long stationId) {
        return vehicleService.findById(id)
                .map(vehicle -> vehicleService.updateVehicleStationId(vehicle, stationId))
                .map(vehicleMapper::vehicleToVehicleDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/status/{id}")
    public ResponseEntity<List<VehicleDTO>> findByVehicleStatusId(@PathVariable int id) {
        return ResponseEntity.ok(vehicleService.findByVehicleStatusId(id).stream()
                .map(vehicleMapper::vehicleToVehicleDTO)
                .collect(Collectors.toList()));
    }


    @GetMapping("/station/{stationId}")
    public ResponseEntity<List<VehicleDTO>> findByStationId(@PathVariable Long stationId) {
        return ResponseEntity.ok(vehicleService.findByStationId(stationId).stream()
                .map(vehicleMapper::vehicleToVehicleDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping("/station/{stationId}/status/{vehicleStatusId}")
    public ResponseEntity<List<VehicleDTO>> findByStationId(@PathVariable Long stationId, @PathVariable int vehicleStatusId) {
        return ResponseEntity.ok(vehicleService.findByStationIdAndVehicleStatusId(stationId, vehicleStatusId).stream()
                .map(vehicleMapper::vehicleToVehicleDTO)
                .collect(Collectors.toList()));
    }

    @Autowired
    public void setVehicleService(IVehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @Autowired
    public void setVehicleMapper(VehicleMapper vehicleMapper) {
        this.vehicleMapper = vehicleMapper;
    }
}
