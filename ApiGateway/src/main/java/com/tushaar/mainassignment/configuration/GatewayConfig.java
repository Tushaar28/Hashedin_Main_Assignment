package com.tushaar.mainassignment.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

//	@Bean
//	public RouteLocator myRoutes(RouteLocatorBuilder routeLocatorBuilder) {
//		return routeLocatorBuilder.routes()
//				.route(p -> p.path("/users/**").uri("http://localhost:9000"))
//				.route(p -> p.path("/orders/**").uri("http://localhost:9001")).build();
//	}

//	@Bean
//	public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
//		return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
//				.circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
//				.timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(2)).build()).build());
//	}
//
//	@Bean
//	public SpringCloudCircuitBreakerFilterFactory springCloudCircuitBreakerFilterFactory() {
//		return (SpringCloudCircuitBreakerFilterFactory) defaultCustomizer();
//	}
}
