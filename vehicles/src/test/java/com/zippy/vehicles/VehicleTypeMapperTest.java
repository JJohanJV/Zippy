package com.zippy.vehicles;

import com.zippy.vehicles.dto.VehicleTypeDTO;
import com.zippy.vehicles.mappers.VehicleTypeMapper;
import com.zippy.vehicles.model.VehicleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VehicleTypeMapperTest {

    private VehicleTypeMapper vehicleTypeMapper = Mappers.getMapper(VehicleTypeMapper.class);

    private VehicleType vehicleType;
    private VehicleTypeDTO vehicleTypeDTO;

    @BeforeEach
    public void setUp() {
        vehicleType = new VehicleType();
        vehicleType.setId(1);
        vehicleType.setName("BYKE");

        vehicleTypeDTO = VehicleTypeDTO.builder()
                .id(1)
                .name("BYKE")
                .build();
    }

    // Intención: Verificar que el método vehicleTypeToVehicleTypeDTO realice correctamente el mapeo de VehicleType a VehicleTypeDTO.
    @Test
    public void testVehicleTypeToVehicleTypeDTO() {
        VehicleTypeDTO result = vehicleTypeMapper.vehicleTypeToVehicleTypeDTO(vehicleType);

        assertEquals(vehicleTypeDTO.getId(), result.getId());
        assertEquals(vehicleTypeDTO.getName(), result.getName());
    }
}

