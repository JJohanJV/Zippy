package com.zippy.security.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest implements java.io.Serializable {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotNull
    private Long personalInformationId;
    @NotNull
    private Long roleId;
}
