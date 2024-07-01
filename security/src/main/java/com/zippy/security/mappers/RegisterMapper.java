package com.zippy.security.mappers;

import com.zippy.security.DTO.RegisterRequest;
import com.zippy.security.model.Credential;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface RegisterMapper {
    Credential RegisterRequestToCredential(RegisterRequest registerRequest);
}
