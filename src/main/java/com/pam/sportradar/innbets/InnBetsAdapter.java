package com.pam.sportradar.innbets;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "INNBets SPORT Feed Adapter"
		)
)
public class InnBetsAdapter {
	public static void main(String[] args) {
		SpringApplication.run(InnBetsAdapter.class, args);
	}
}

