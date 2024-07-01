package com.zippy.vehicles;

import com.zippy.vehicles.model.VehicleStatus;
import com.zippy.vehicles.repository.IVehicleStatusRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class VehicleStatusRepositoryTest {

    @Autowired
    private IVehicleStatusRepository vehicleStatusRepository;

    @Test
    void testCreateReadUpdateDeleteVehicleStatus() {
        // Crear
        VehicleStatus vehicleStatus = vehicleStatusRepository.save(VehicleStatus.builder().name("AVAILABLE").build());
        assertThat(vehicleStatus.getId()).isGreaterThan(0);

        // Leer
        VehicleStatus found = vehicleStatusRepository.findById(vehicleStatus.getId()).orElse(null);
        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo("AVAILABLE");

        // Actualizar
        found.setName("UNAVAILABLE");
        vehicleStatusRepository.save(found);
        VehicleStatus updated = vehicleStatusRepository.findById(vehicleStatus.getId()).orElse(null);
        assertThat(updated.getName()).isEqualTo("UNAVAILABLE");

        // Eliminar
        vehicleStatusRepository.deleteById(vehicleStatus.getId());
        VehicleStatus deleted = vehicleStatusRepository.findById(vehicleStatus.getId()).orElse(null);
        assertThat(deleted).isNull();
    }
}
