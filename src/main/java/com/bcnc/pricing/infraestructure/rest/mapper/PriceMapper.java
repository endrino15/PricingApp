package com.bcnc.pricing.infraestructure.rest.mapper;

import org.springframework.stereotype.Component;

import com.bcnc.pricing.domain.model.Price;
import com.bcnc.pricing.infraestructure.persistence.entity.PriceEntity;

@Component
public class PriceMapper {
	public Price toDomain(PriceEntity entity) {
		return new Price(entity.getBrandId(), entity.getProductId(), entity.getPriceList(), entity.getStartDate(),
				entity.getEndDate(), entity.getPriority(), entity.getPrice(), entity.getCurrency());
	}
}
