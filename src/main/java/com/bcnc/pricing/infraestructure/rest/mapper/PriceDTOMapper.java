package com.bcnc.pricing.infraestructure.rest.mapper;

import org.springframework.stereotype.Component;

import com.bcnc.pricing.domain.model.Price;
import com.bcnc.pricing.infraestructure.rest.dto.PriceDTOOUT;

@Component
public class PriceDTOMapper {
    // Price → DTOOUT
    public PriceDTOOUT toDTO(Price price) {
        return new PriceDTOOUT(
                price.getBrandId(),
                price.getProductId(),
                price.getPriceList(),
                price.getStartDate(),
                price.getEndDate(),
                price.getPrice(),
                price.getCurrency()
        );
    }
}

