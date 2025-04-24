package com.example.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

@EnableDiscoveryClient
@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
		return builder.routes()
				.route("microservice1",
						r -> r.path("/mic1/**")
								.uri("http://localhost:8081"))
				.route("microservice2",
						r -> r.path("/mic2/**")
								.uri("http://localhost:8082"))
				.route("microservice3",
						r -> r.path("/mic3/**")
								.uri("http://localhost:8083"))
				.route("microservice4",
						r -> r.path("/mic4/**")
								.uri("http://localhost:8084"))
				.route("microservice5",
						r -> r.path("/mic5/**")
								.uri("http://localhost:8085"))
				.route("microservice6",
						r -> r.path("/mic6/**")
								.uri("http://localhost:8086"))
				.build();
	}


}
