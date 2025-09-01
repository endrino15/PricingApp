package com.bcnc.pricing.infraestructure.rest.mapper;

import org.springframework.stereotype.Component;

import com.bcnc.pricing.domain.model.PriceCriteria;
import com.bcnc.pricing.infraestructure.rest.dto.PriceDTOIN;

@Component
public class PriceCriteriaMapper {
    public PriceCriteria toCriteria(PriceDTOIN dtoIn) {
        return new PriceCriteria(dtoIn.brandId(), dtoIn.productId(), dtoIn.applicationDate());
    }
}
