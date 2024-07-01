package com.zippy.vehicles;

import com.zippy.vehicles.dto.VehicleStatusDTO;
import com.zippy.vehicles.mappers.VehicleStatusMapper;
import com.zippy.vehicles.model.VehicleStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VehicleStatusMapperTest {

    private VehicleStatusMapper vehicleStatusMapper = Mappers.getMapper(VehicleStatusMapper.class);

    private VehicleStatus vehicleStatus;
    private VehicleStatusDTO vehicleStatusDTO;

    @BeforeEach
    public void setUp() {
        vehicleStatus = new VehicleStatus();
        vehicleStatus.setId(1);
        vehicleStatus.setName("Available");

        vehicleStatusDTO = VehicleStatusDTO.builder()
                .id(1)
                .name("Available")
                .build();
    }

    // Intención: Verificar que el método vehicleStatusToVehicleStatusDTO realice correctamente el mapeo de VehicleStatus a VehicleStatusDTO.
    @Test
    public void testVehicleStatusToVehicleStatusDTO() {
        VehicleStatusDTO result = vehicleStatusMapper.vehicleStatusToVehicleStatusDTO(vehicleStatus);

        assertEquals(vehicleStatusDTO.getId(), result.getId());
        assertEquals(vehicleStatusDTO.getName(), result.getName());
    }
}
