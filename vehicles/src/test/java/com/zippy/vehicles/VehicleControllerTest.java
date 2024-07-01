package com.zippy.vehicles;

import com.zippy.vehicles.controller.VehicleController;
import com.zippy.vehicles.dto.VehicleDTO;
import com.zippy.vehicles.mappers.VehicleMapper;
import com.zippy.vehicles.model.Vehicle;
import com.zippy.vehicles.service.interfaces.IVehicleService;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VehicleController.class)
public class VehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IVehicleService vehicleService;

    @MockBean
    private VehicleMapper vehicleMapper;

    private VehicleDTO vehicleDTO;

    @BeforeEach
    public void setUp() {
        vehicleDTO = VehicleDTO.builder()
                .id(1L)
                .type("1") // Asumiendo valores entre "1" y "2"
                .status("1") // Asumiendo valores entre "1" y "4"
                .stationId(1L) // Asumiendo valores entre "1" y "8"
                .electric(true)
                .batteryLevel(50)
                .build();
    }

    // Intención: Verificar que el endpoint /all devuelva la lista completa de vehículos en formato JSON correctamente.
    @Test
    public void testFindAll() throws Exception {
        when(vehicleService.findAll()).thenReturn(Arrays.asList(new Vehicle()));
        when(vehicleMapper.vehicleToVehicleDTO(any(Vehicle.class))).thenReturn(vehicleDTO);

        mockMvc.perform(get("/api/v1/vehicles/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[{'id': 1, 'type': '1', 'status': '1', 'stationId': 1, 'electric': true, 'batteryLevel': 50}]"));
    }

    // Intención: Verificar que el endpoint /{id} devuelva el vehículo correspondiente al ID proporcionado en formato JSON correctamente.
    @Test
    public void testFindById() throws Exception {
        when(vehicleService.findById(anyLong())).thenReturn(Optional.of(new Vehicle()));
        when(vehicleMapper.vehicleToVehicleDTO(any(Vehicle.class))).thenReturn(vehicleDTO);

        mockMvc.perform(get("/api/v1/vehicles/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{'id': 1, 'type': '1', 'status': '1', 'stationId': 1, 'electric': true, 'batteryLevel': 50}"));
    }

    // Intención: Verificar que el endpoint /{vehicleId}/status/{statusId} actualice el estado del vehículo y devuelva el vehículo actualizado en formato JSON correctamente.
    @Test
    public void testUpdateVehicleStatus() throws Exception {
        when(vehicleService.findById(anyLong())).thenReturn(Optional.of(new Vehicle()));
        when(vehicleService.updateVehicleStatusId(any(Vehicle.class), anyInt())).thenReturn(Optional.of(new Vehicle()));
        when(vehicleMapper.vehicleToVehicleDTO(any(Vehicle.class))).thenReturn(vehicleDTO);

        mockMvc.perform(put("/api/v1/vehicles/1/status/2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{'id': 1, 'type': '1', 'status': '1', 'stationId': 1, 'electric': true, 'batteryLevel': 50}"));
    }

    // Intención: Verificar que el endpoint /{id}/station/{stationId} actualice la estación del vehículo y devuelva el vehículo actualizado en formato JSON correctamente.
    @Test
    public void testUpdateVehicleStation() throws Exception {
        when(vehicleService.findById(anyLong())).thenReturn(Optional.of(new Vehicle()));
        when(vehicleService.updateVehicleStationId(any(Vehicle.class), anyLong())).thenReturn(new Vehicle());
        when(vehicleMapper.vehicleToVehicleDTO(any(Vehicle.class))).thenReturn(vehicleDTO);

        mockMvc.perform(put("/api/v1/vehicles/1/station/2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{'id': 1, 'type': '1', 'status': '1', 'stationId': 1, 'electric': true, 'batteryLevel': 50}"));
    }

    // Intención: Verificar que el endpoint /status/{id} devuelva la lista de vehículos con el estado especificado en formato JSON correctamente.
    @Test
    public void testFindByVehicleStatusId() throws Exception {
        when(vehicleService.findByVehicleStatusId(anyInt())).thenReturn(Arrays.asList(new Vehicle()));
        when(vehicleMapper.vehicleToVehicleDTO(any(Vehicle.class))).thenReturn(vehicleDTO);

        mockMvc.perform(get("/api/v1/vehicles/status/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[{'id': 1, 'type': '1', 'status': '1', 'stationId': 1, 'electric': true, 'batteryLevel': 50}]"));
    }

    // Intención: Verificar que el endpoint /station/{stationId} devuelva la lista de vehículos en la estación especificada en formato JSON correctamente.
    @Test
    public void testFindByStationId() throws Exception {
        when(vehicleService.findByStationId(anyLong())).thenReturn(Arrays.asList(new Vehicle()));
        when(vehicleMapper.vehicleToVehicleDTO(any(Vehicle.class))).thenReturn(vehicleDTO);

        mockMvc.perform(get("/api/v1/vehicles/station/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[{'id': 1, 'type': '1', 'status': '1', 'stationId': 1, 'electric': true, 'batteryLevel': 50}]"));
    }

    // Intención: Verificar que el endpoint /station/{stationId}/status/{vehicleStatusId} devuelva la lista de vehículos en la estación y con el estado especificados en formato JSON correctamente.
    @Test
    public void testFindByStationIdAndVehicleStatusId() throws Exception {
        when(vehicleService.findByStationIdAndVehicleStatusId(anyLong(), anyInt())).thenReturn(Arrays.asList(new Vehicle()));
        when(vehicleMapper.vehicleToVehicleDTO(any(Vehicle.class))).thenReturn(vehicleDTO);

        mockMvc.perform(get("/api/v1/vehicles/station/1/status/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[{'id': 1, 'type': '1', 'status': '1', 'stationId': 1, 'electric': true, 'batteryLevel': 50}]"));
    }
}





