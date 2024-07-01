package com.zippy.vehicles;

import com.zippy.vehicles.model.VehicleType;
import com.zippy.vehicles.repository.IVehicleTypeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class VehicleTypeRepositoryTest {

    @Autowired
    private IVehicleTypeRepository vehicleTypeRepository;

    @Test
    void testCreateReadUpdateDeleteVehicleType() {
        // Crear
        VehicleType vehicleType = vehicleTypeRepository.save(VehicleType.builder().name("BIKE").build());
        assertThat(vehicleType.getId()).isGreaterThan(0);

        // Leer
        VehicleType found = vehicleTypeRepository.findById(vehicleType.getId()).orElse(null);
        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo("BIKE");

        // Actualizar
        found.setName("E-BIKE");
        vehicleTypeRepository.save(found);
        VehicleType updated = vehicleTypeRepository.findById(vehicleType.getId()).orElse(null);
        assertThat(updated.getName()).isEqualTo("E-BIKE");

        // Eliminar
        vehicleTypeRepository.deleteById(vehicleType.getId());
        VehicleType deleted = vehicleTypeRepository.findById(vehicleType.getId()).orElse(null);
        assertThat(deleted).isNull();
    }
}
