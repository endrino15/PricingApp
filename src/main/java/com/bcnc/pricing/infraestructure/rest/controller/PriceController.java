package com.bcnc.pricing.infraestructure.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bcnc.pricing.aplication.port.in.GetPriceUseCase;
import com.bcnc.pricing.domain.model.PriceCriteria;
import com.bcnc.pricing.infraestructure.rest.dto.PriceDTOIN;
import com.bcnc.pricing.infraestructure.rest.dto.PriceDTOOUT;
import com.bcnc.pricing.infraestructure.rest.mapper.PriceCriteriaMapper;
import com.bcnc.pricing.infraestructure.rest.mapper.PriceDTOMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/price")
public class PriceController {

	private final GetPriceUseCase getPriceUseCase;	
	private final PriceDTOMapper priceDTOMapper;
	private final PriceCriteriaMapper priceCriteriaMapper;


	@GetMapping
	public ResponseEntity<PriceDTOOUT> getPrice(@ModelAttribute PriceDTOIN priceDTOIN) {
		PriceCriteria priceCriteria= priceCriteriaMapper.toCriteria(priceDTOIN);
		return ResponseEntity.ok(priceDTOMapper.toDTO(getPriceUseCase.getApplicablePrice(priceCriteria)));
	}
}
