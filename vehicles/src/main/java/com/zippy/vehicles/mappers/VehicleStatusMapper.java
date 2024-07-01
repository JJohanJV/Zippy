package com.zippy.vehicles.mappers;

import com.zippy.vehicles.dto.VehicleStatusDTO;
import com.zippy.vehicles.model.VehicleStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VehicleStatusMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    VehicleStatusDTO vehicleStatusToVehicleStatusDTO(VehicleStatus vehicleStatus);
}
