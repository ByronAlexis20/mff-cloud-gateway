package mff.cloud.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import mff.cloud.gateway.filter.JwtAuthenticationFilter;

@Configuration
public class GatewayConfig {

	@Autowired
	private JwtAuthenticationFilter filter;

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("auth", r -> r.path("/api/auth/**").uri("http://localhost:4040/"))
				.route("auth-empresa", r -> r.path("/nw-auth/empresa/**").uri("http://localhost:8080/"))
				.route("nw-seguridad", r -> r.path("/nw-seguridad/**").filters(f -> f.filter(filter)).uri("http://localhost:6001/"))
				.route("error", r -> r.path("/error/**").uri("http://localhost:8080/")).build();
	}
	
}