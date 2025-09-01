package com.bcnc.pricing.infraestructure.persistence.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bcnc.pricing.domain.model.Price;
import com.bcnc.pricing.infraestructure.persistence.entity.PriceEntity;

@Component
public class PriceEntityMapper {

    // PriceEntity → Price
    public Price toDomain(PriceEntity entity) {
        return new Price(
                entity.getBrandId(),
                entity.getProductId(),
                entity.getPriceList(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getPriority(),
                entity.getPrice(),
                entity.getCurrency()
        );
    }

    // Price → PriceEntity
    public PriceEntity toEntity(Price price) {
        PriceEntity entity = new PriceEntity();
        entity.setBrandId(price.getBrandId());
        entity.setProductId(price.getProductId());
        entity.setPriceList(price.getPriceList());
        entity.setStartDate(price.getStartDate());
        entity.setEndDate(price.getEndDate());
        entity.setPriority(price.getPriority());
        entity.setPrice(price.getPrice());
        entity.setCurrency(price.getCurrency());
        return entity;
    }

    // Lista de entities → lista de dominio
    public List<Price> toDomainList(List<PriceEntity> entities) {
        return entities.stream()
                       .map(this::toDomain)
                       .toList();
    }
}
