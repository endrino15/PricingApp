package com.bcnc.pricing.aplication.port.in;

import java.time.LocalDateTime;

import com.bcnc.pricing.domain.model.Price;

public interface GetPriceUseCase {
    Price getApplicablePrice(Long brandId, Long productId, LocalDateTime applicationDate);
}
