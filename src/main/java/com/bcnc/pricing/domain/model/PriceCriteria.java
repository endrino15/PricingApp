package com.bcnc.pricing.domain.model;

import java.time.LocalDateTime;

public record PriceCriteria(long brandId, long productId, LocalDateTime applicationDate) {
}
