package com.bcnc.pricing.infraestructure.rest.dto;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

public record PriceDTOIN(
        long brandId,
        long productId,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        LocalDateTime applicationDate
) {}

