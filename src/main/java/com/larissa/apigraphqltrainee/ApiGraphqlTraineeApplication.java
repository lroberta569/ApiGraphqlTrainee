package com.larissa.apigraphqltrainee;

import com.larissa.apigraphqltrainee.model.Base64URL;
import com.larissa.apigraphqltrainee.service.AwsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ApiGraphqlTraineeApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(ApiGraphqlTraineeApplication.class, args);
	}

	@Bean
	public CorsFilter corsFilter() {
		List<String> origins = new ArrayList<>();
		origins.add("http://127.0.0.1:5173/");
		origins.add("http://10.120.0.16:9280/");
		origins.add("https://trainee-gamma.vercel.app/");
		origins.add("https://trainee-graphql.vercel.app/");

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.setAllowedOrigins(origins);
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/graphql/**", config);
		return new CorsFilter(source);
	}
}
