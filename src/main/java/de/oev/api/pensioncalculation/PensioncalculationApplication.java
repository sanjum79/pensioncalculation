package de.oev.api.pensioncalculation;

import de.oev.api.pensioncalculation.rest.PensionCalculationResource;
import de.oev.api.pensioncalculation.service.PensionCalculationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PensioncalculationApplication {

	public static void main(String[] args) {
		SpringApplication.run(PensioncalculationApplication.class, args);
	}

	@Bean
	public PensionCalculationService pensionCalculationService() {
		return new PensionCalculationService();
	}

	@Bean
	public PensionCalculationResource pensionCalculationResource() {
		return new PensionCalculationResource(pensionCalculationService());
	}
}
