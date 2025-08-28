package com.bcnc.pricing.infraestructure.web.dto;

import java.time.LocalDateTime;

public record PriceDTO(
        Long brandId,
        Long productId,
        Long priceList,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Double price,
        String currency
) {}