package com.zippy.vehicles;

import com.zippy.vehicles.model.Vehicle;
import com.zippy.vehicles.model.VehicleStatus;
import com.zippy.vehicles.model.VehicleType;
import com.zippy.vehicles.repository.IVehicleRepository;
import com.zippy.vehicles.repository.IVehicleStatusRepository;
import com.zippy.vehicles.repository.IVehicleTypeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class VehicleRepositoryTest {

    @Autowired
    private IVehicleRepository vehicleRepository;

    @Autowired
    private IVehicleTypeRepository vehicleTypeRepository;

    @Autowired
    private IVehicleStatusRepository vehicleStatusRepository;

    @Test
    @Rollback(false)
    void testCreateReadUpdateDeleteVehicle() {
        // Crear entidades auxiliares
        VehicleType bikeType = vehicleTypeRepository.save(VehicleType.builder().name("BIKE").build());
        VehicleStatus availableStatus = vehicleStatusRepository.save(VehicleStatus.builder().name("AVAILABLE").build());

        // Crear
        Vehicle vehicle = Vehicle.builder()
                .vehicleTypeId(bikeType.getId())
                .vehicleStatusId(availableStatus.getId())
                .electric(true)
                .batteryLevel(100)
                .stationId(1L)
                .build();
        vehicle = vehicleRepository.save(vehicle);
        assertThat(vehicle.getId()).isGreaterThan(0);

        // Leer
        Vehicle found = vehicleRepository.findById(vehicle.getId()).orElse(null);
        assertThat(found).isNotNull();
        assertThat(found.getBatteryLevel()).isEqualTo(100);

        // Actualizar
        found.setBatteryLevel(80);
        vehicleRepository.save(found);
        Vehicle updated = vehicleRepository.findById(vehicle.getId()).orElse(null);
        assertThat(updated.getBatteryLevel()).isEqualTo(80);

        // Eliminar
        vehicleRepository.deleteById(vehicle.getId());
        Vehicle deleted = vehicleRepository.findById(vehicle.getId()).orElse(null);
        assertThat(deleted).isNull();
    }
}
