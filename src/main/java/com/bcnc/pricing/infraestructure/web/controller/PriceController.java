package com.bcnc.pricing.infraestructure.web.controller;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bcnc.pricing.aplication.port.in.GetPriceUseCase;
import com.bcnc.pricing.domain.model.Price;
import com.bcnc.pricing.infraestructure.web.dto.PriceDTO;

@RestController
@RequestMapping("/price")
public class PriceController {

	private final GetPriceUseCase getPriceUseCase;

	public PriceController(GetPriceUseCase getPriceUseCase) {
		this.getPriceUseCase = getPriceUseCase;
	}

	@GetMapping
	public PriceDTO getPrice(@RequestParam Long brandId, @RequestParam Long productId,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) String applicationDate) {
		LocalDateTime date = LocalDateTime.parse(applicationDate);
		Price price = getPriceUseCase.getApplicablePrice(brandId, productId, date);
		return new PriceDTO(price.getBrandId(), price.getProductId(), price.getPriceList(), price.getStartDate(),
				price.getEndDate(), price.getPrice(), price.getCurrency());
	}
}
