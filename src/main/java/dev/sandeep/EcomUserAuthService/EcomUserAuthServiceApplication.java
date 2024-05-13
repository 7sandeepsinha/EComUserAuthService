package dev.sandeep.EcomUserAuthService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class EcomUserAuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcomUserAuthServiceApplication.class, args);
	}

}
