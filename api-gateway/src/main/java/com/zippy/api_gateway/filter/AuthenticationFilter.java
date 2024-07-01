package com.zippy.api_gateway.filter;

import com.netflix.discovery.EurekaClient;
import com.zippy.api_gateway.client.AuthService;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {



    private RouteValidator routeValidator;


    private EurekaClient eurekaClient;

    private AuthService authService;

    private RestTemplate template;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if(routeValidator.isSecured.test(exchange.getRequest())){
                if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                    throw new RuntimeException("Missing Authorization Header");
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);

                if(authHeader != null && authHeader.startsWith("Bearer ")){
                    authHeader = authHeader.substring(7);

                }
//                    authService.validateToken(authHeader);

//                    template.getForObject("http://AuthService/auth/validate?token=" + authHeader, String.class);

                try {
                    String serverId = "AuthService";
                    String authServiceUrl = eurekaClient.getNextServerFromEureka(serverId, false).getHomePageUrl();
                    template.getForObject(authServiceUrl+"auth/validate?token=" + authHeader, String.class);

                }catch (Exception e){
                    throw new RuntimeException("Unauthorized access to application");

                }

            }
            return chain.filter(exchange);
        });
    }

    public static class Config{

    }

    @Autowired
    public  void  setRouteValidator(RouteValidator routeValidator){
        this.routeValidator = routeValidator;
    }

    @Autowired
    public  void  setAuthService(@Lazy AuthService authService){
        this.authService = authService;
    }

    @Autowired
    public void  setTemplate(RestTemplate restTemplate){
        this.template = restTemplate;
    }

    @Autowired
    public void setEurekaClient(EurekaClient eurekaClient){
        this.eurekaClient = eurekaClient;
    }
}
