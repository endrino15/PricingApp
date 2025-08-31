package com.bcnc.pricing.infraestructure.web.controller;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bcnc.pricing.aplication.port.in.GetPriceUseCase;
import com.bcnc.pricing.infraestructure.db.mapper.PriceDTOMapper;
import com.bcnc.pricing.infraestructure.web.dto.PriceDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/price")
public class PriceController {

	private final GetPriceUseCase getPriceUseCase;	
	private final PriceDTOMapper priceDTOMapper;

	@GetMapping
	public ResponseEntity<PriceDTO> getPrice(@RequestParam long brandId, @RequestParam long productId,
			@RequestParam("applicationDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
		return ResponseEntity.ok(priceDTOMapper.toDTO(getPriceUseCase.getApplicablePrice(brandId, productId, date)));
	}
}
