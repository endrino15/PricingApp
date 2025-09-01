package com.bcnc.pricing.aplication.port.out;

import java.util.List;

import com.bcnc.pricing.domain.model.Price;
import com.bcnc.pricing.domain.model.PriceCriteria;

public interface LoadPricePort {
	List<Price> findPrices(PriceCriteria priceCriteria);
}
