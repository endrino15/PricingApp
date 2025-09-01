package com.bcnc.pricing.infraestructure.rest.dto;

import java.time.LocalDateTime;

public record PriceDTOOUT(
        long brandId,
        long productId,
        long priceList,
        LocalDateTime startDate,
        LocalDateTime endDate,
        double price,
        String currency
) {}