package com.bcnc.pricing.domain.service;

import java.util.Comparator;
import java.util.List;

import com.bcnc.pricing.domain.model.Price;
import com.bcnc.pricing.exception.PriceNotFoundException;

public class PriceSelector {

	public static Price selectHighestPriority(List<Price> prices) {
		return prices.stream().max(Comparator.comparingInt(Price::getPriority))
				.orElseThrow(() -> new PriceNotFoundException("No applicable price found"));

	}
}
