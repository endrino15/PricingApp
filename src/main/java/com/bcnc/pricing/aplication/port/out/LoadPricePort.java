package com.bcnc.pricing.aplication.port.out;

import java.time.LocalDateTime;
import java.util.List;

import com.bcnc.pricing.domain.model.Price;

public interface LoadPricePort {
    List<Price> findPrices(Long brandId, Long productId, LocalDateTime applicationDate);
}
