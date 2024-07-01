package com.zippy.vehicles;

import com.zippy.vehicles.controller.VehicleTypeController;
import com.zippy.vehicles.dto.VehicleTypeDTO;
import com.zippy.vehicles.mappers.VehicleTypeMapper;
import com.zippy.vehicles.model.VehicleType;
import com.zippy.vehicles.service.interfaces.IVehicleTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VehicleTypeController.class)
public class VehicleTypeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IVehicleTypeService vehicleTypeService;

    @MockBean
    private VehicleTypeMapper vehicleTypeMapper;

    private VehicleTypeDTO vehicleTypeDTOByke;

    @BeforeEach
    public void setUp() {
        vehicleTypeDTOByke = VehicleTypeDTO.builder()
                .id(1)
                .name("BYKE")
                .build();
    }

    // Intención: Verificar que el endpoint /all devuelva la lista completa de tipos de vehículos en formato JSON correctamente.
    @Test
    public void testFindAll() throws Exception {
        when(vehicleTypeService.findAll()).thenReturn(Arrays.asList(new VehicleType()));
        when(vehicleTypeMapper.vehicleTypeToVehicleTypeDTO(any(VehicleType.class))).thenReturn(vehicleTypeDTOByke);

        mockMvc.perform(get("/api/v1/vehicleType/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[{'id': 1, 'name': 'BYKE'}]"));
    }

    // Intención: Verificar que el endpoint /{id} devuelva el tipo de vehículo correspondiente al ID proporcionado en formato JSON correctamente.
    @Test
    public void testFindById() throws Exception {
        when(vehicleTypeService.findById(anyInt())).thenReturn(Optional.of(new VehicleType()));
        when(vehicleTypeMapper.vehicleTypeToVehicleTypeDTO(any(VehicleType.class))).thenReturn(vehicleTypeDTOByke);

        mockMvc.perform(get("/api/v1/vehicleType/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{'id': 1, 'name': 'BYKE'}"));
    }
}

