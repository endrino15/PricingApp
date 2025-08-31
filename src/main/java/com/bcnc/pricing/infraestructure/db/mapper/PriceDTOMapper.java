package com.bcnc.pricing.infraestructure.db.mapper;

import org.springframework.stereotype.Component;

import com.bcnc.pricing.domain.model.Price;
import com.bcnc.pricing.infraestructure.web.dto.PriceDTO;

@Component
public class PriceDTOMapper {
    public PriceDTO toDTO(Price price) {
        return new PriceDTO(
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