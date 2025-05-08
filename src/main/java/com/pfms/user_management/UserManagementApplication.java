package com.pfms.user_management;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "PFMS User Management Service API",
				version = "v1",
				contact = @Contact(
						name = "PFMS",
						email = "PFMS@example.com",
						url = "https://www.PFMS.com"
				),
				license= @License(
						name = "Apache 2.0",
						url = "https://www.PFMS.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "PFMS Documentation",
				url = "https://www.PFMS.com"
		)
)
public class UserManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserManagementApplication.class, args);
	}

}
