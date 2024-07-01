package com.zippy.api_gateway.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "authorizate", url = "http://AUTHSERVICE")
public interface AuthService {

    @GetMapping("/auth/validate")
    String  validateToken(@RequestParam String token);
}
