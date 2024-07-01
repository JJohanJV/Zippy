package com.zippy.vehicles.mappers;

import com.zippy.vehicles.dto.VehicleTypeDTO;
import com.zippy.vehicles.model.VehicleType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VehicleTypeMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    VehicleTypeDTO vehicleTypeToVehicleTypeDTO(VehicleType vehicleType);
}
