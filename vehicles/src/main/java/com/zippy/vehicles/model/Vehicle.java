package com.zippy.vehicles.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity
@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vehicle")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column(name = "vehicle_type_id")
    private int vehicleTypeId;

    @Column(name = "vehicle_status_id")
    private int vehicleStatusId;

    @Column(name = "electric")
    private boolean electric;

    @Column(name = "battery")
    private int batteryLevel;

    @Column(name = "station_id")
    private long stationId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_type_id", insertable = false, updatable = false)
    private VehicleType vehicleType;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_status_id", insertable = false, updatable = false)
    private VehicleStatus vehicleStatus;
}
