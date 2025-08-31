package com.bcnc.pricing.domain.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import com.bcnc.pricing.domain.model.Price;

public class PriceSelector {

	public static Optional<Price> selectHighestPriority(List<Price> prices) {
		return prices.stream().max(Comparator.comparingInt(Price::getPriority));
	}
}
