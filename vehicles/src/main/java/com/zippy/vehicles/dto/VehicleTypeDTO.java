package com.zippy.vehicles.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class VehicleTypeDTO {
    @NotNull
    private int id;

    @NotEmpty
    private String name;
}
