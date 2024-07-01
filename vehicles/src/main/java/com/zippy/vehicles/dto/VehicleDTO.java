package com.zippy.vehicles.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDTO {
    @NotNull
    private Long id;

    @NotEmpty
    private String type;

    @NotEmpty
    private String status;

    @NotNull
    private Long stationId;

    @NotEmpty
    private boolean electric;

    @Min(0)
    @Max(100)
    private int batteryLevel;
}
