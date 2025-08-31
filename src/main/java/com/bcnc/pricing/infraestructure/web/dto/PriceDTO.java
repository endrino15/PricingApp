package com.bcnc.pricing.infraestructure.web.dto;

import java.time.LocalDateTime;

public record PriceDTO(
        long brandId,
        long productId,
        long priceList,
        LocalDateTime startDate,
        LocalDateTime endDate,
        double price,
        String currency
) {}