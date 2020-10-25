package com.sg.loan.frontoffice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FrontOfficeApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrontOfficeApplication.class, args);
	}

}
