package com.zippy.vehicles;

import com.zippy.vehicles.controller.VehicleStatusController;
import com.zippy.vehicles.dto.VehicleStatusDTO;
import com.zippy.vehicles.mappers.VehicleStatusMapper;
import com.zippy.vehicles.model.VehicleStatus;
import com.zippy.vehicles.service.interfaces.IVehicleStatusService;
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

@WebMvcTest(VehicleStatusController.class)
public class VehicleStatusControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IVehicleStatusService vehicleStatusService;

    @MockBean
    private VehicleStatusMapper vehicleStatusMapper;

    private VehicleStatusDTO vehicleStatusDTO;

    @BeforeEach
    public void setUp() {
        vehicleStatusDTO = VehicleStatusDTO.builder()
                .id(1)
                .name("Available")
                .build();
    }

    // Intención: Verificar que el endpoint /all devuelva la lista completa de estados de vehículos en formato JSON correctamente.
    @Test
    public void testFindAll() throws Exception {
        when(vehicleStatusService.findAll()).thenReturn(Arrays.asList(new VehicleStatus()));
        when(vehicleStatusMapper.vehicleStatusToVehicleStatusDTO(any(VehicleStatus.class))).thenReturn(vehicleStatusDTO);

        mockMvc.perform(get("/api/v1/vehicleStatus/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[{'id': 1, 'name': 'Available'}]"));
    }

    // Intención: Verificar que el endpoint /{id} devuelva el estado de vehículo correspondiente al ID proporcionado en formato JSON correctamente.
    @Test
    public void testFindById() throws Exception {
        when(vehicleStatusService.findById(anyInt())).thenReturn(Optional.of(new VehicleStatus()));
        when(vehicleStatusMapper.vehicleStatusToVehicleStatusDTO(any(VehicleStatus.class))).thenReturn(vehicleStatusDTO);

        mockMvc.perform(get("/api/v1/vehicleStatus/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{'id': 1, 'name': 'Available'}"));
    }
}
