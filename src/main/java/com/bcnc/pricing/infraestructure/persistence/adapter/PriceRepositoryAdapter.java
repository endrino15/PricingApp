package com.bcnc.pricing.infraestructure.persistence.adapter;

import com.bcnc.pricing.aplication.port.out.LoadPricePort;
import com.bcnc.pricing.domain.model.Price;
import com.bcnc.pricing.infraestructure.persistence.entity.PriceEntity;
import com.bcnc.pricing.infraestructure.persistence.jpa.JpaPriceRepository;
import com.bcnc.pricing.infraestructure.rest.mapper.PriceMapper;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Component
public class PriceRepositoryAdapter implements LoadPricePort {

	private final JpaPriceRepository jpaPriceRepository;
	private final PriceMapper priceMapper;

	 @Override
	    public List<Price> findPrices(long brandId, long productId, LocalDateTime applicationDate) {
		 List<PriceEntity> entities = jpaPriceRepository.findApplicablePrice(brandId, productId, applicationDate);
		    return entities.stream()
		                   .map(priceMapper::toDomain)
		                   .toList();
	    }
}
