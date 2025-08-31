package com.bcnc.pricing.application.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bcnc.pricing.aplication.port.in.GetPriceUseCase;
import com.bcnc.pricing.aplication.port.out.LoadPricePort;
import com.bcnc.pricing.domain.model.Price;
import com.bcnc.pricing.domain.service.PriceSelector;
import com.bcnc.pricing.exception.PriceNotFoundException;

@Service
public class PriceService implements GetPriceUseCase {

	private final LoadPricePort loadPricePort;

	public PriceService(LoadPricePort loadPricePort) {
		this.loadPricePort = loadPricePort;
	}

	@Override
	public Price getApplicablePrice(long brandId, long productId, LocalDateTime applicationDate) {
		  List<Price> prices = loadPricePort.findPrices(brandId, productId, applicationDate);
		    return PriceSelector.selectHighestPriority(prices)
		            .orElseThrow(() -> new PriceNotFoundException(
		                    String.format("No prices found for brandId=%d, productId=%d at %s",
		                            brandId, productId, applicationDate)));
	}
}
