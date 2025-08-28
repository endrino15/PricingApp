package com.bcnc.pricing.config;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.bcnc.pricing.infraestructure.persistence.entity.PriceEntity;
import com.bcnc.pricing.infraestructure.persistence.jpa.JpaPriceRepository;

@Configuration
public class H2DataInitializer implements CommandLineRunner {

	private final JpaPriceRepository repository;

	public H2DataInitializer(JpaPriceRepository repository) {
		this.repository = repository;
	}

	@Override
	public void run(String... args) {
		repository.save(new PriceEntity(null, 1L, 35455L, 1L, LocalDateTime.parse("2020-06-14T00:00:00"),
				LocalDateTime.parse("2020-12-31T23:59:59"), 0, 35.50, "EUR"));

		repository.save(new PriceEntity(null, 1L, 35455L, 2L, LocalDateTime.parse("2020-06-14T15:00:00"),
				LocalDateTime.parse("2020-06-14T18:30:00"), 1, 25.45, "EUR"));

		repository.save(new PriceEntity(null, 1L, 35455L, 3L, LocalDateTime.parse("2020-06-15T00:00:00"),
				LocalDateTime.parse("2020-06-15T11:00:00"), 1, 30.50, "EUR"));

		repository.save(new PriceEntity(null, 1L, 35455L, 4L, LocalDateTime.parse("2020-06-15T16:00:00"),
				LocalDateTime.parse("2020-12-31T23:59:59"), 1, 38.95, "EUR"));
	}

}
