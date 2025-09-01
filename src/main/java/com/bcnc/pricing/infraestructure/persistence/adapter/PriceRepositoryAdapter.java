package com.bcnc.pricing.infraestructure.persistence.adapter;

import com.bcnc.pricing.aplication.port.out.LoadPricePort;
import com.bcnc.pricing.domain.model.Price;
import com.bcnc.pricing.domain.model.PriceCriteria;
import com.bcnc.pricing.infraestructure.persistence.entity.PriceEntity;
import com.bcnc.pricing.infraestructure.persistence.jpa.JpaPriceRepository;
import com.bcnc.pricing.infraestructure.persistence.mapper.PriceEntityMapper;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;
import java.util.List;

@RequiredArgsConstructor
@Component
public class PriceRepositoryAdapter implements LoadPricePort {

	private final JpaPriceRepository jpaPriceRepository;
	private final PriceEntityMapper priceMapper;

	 @Override
	    public List<Price> findPrices(PriceCriteria priceCriteria) {
		 List<PriceEntity> entities = jpaPriceRepository.findApplicablePrice(priceCriteria.brandId(),
                 priceCriteria.productId(),
                 priceCriteria.applicationDate());
		    return entities.stream()
		                   .map(priceMapper::toDomain)
		                   .toList();
	    }
}
