package com.bcnc.pricing.infraestructure.persistence.adapter;

import com.bcnc.pricing.aplication.port.out.LoadPricePort;
import com.bcnc.pricing.domain.model.Price;
import com.bcnc.pricing.exception.PriceNotFoundException;
import com.bcnc.pricing.infraestructure.persistence.jpa.JpaPriceRepository;

import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PriceRepositoryAdapter implements LoadPricePort {

	private final JpaPriceRepository jpaPriceRepository;

	public PriceRepositoryAdapter(JpaPriceRepository jpaPriceRepository) {
		this.jpaPriceRepository = jpaPriceRepository;
	}


	@Override
	public List<Price> findPrices(Long brandId, Long productId, LocalDateTime applicationDate) {
        return jpaPriceRepository
                .findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(brandId, productId, applicationDate, applicationDate).stream()
                .map(e -> new Price(
                        e.getBrandId(),
                        e.getProductId(),
                        e.getPriceList(),
                        e.getStartDate(),
                        e.getEndDate(),
                        e.getPriority(),
                        e.getPrice(),
                        e.getCurrency()
                ))
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> {
                            if (list.isEmpty()) {
                                throw new PriceNotFoundException(
	                                    String.format("No prices found for brandId=%d, productId=%d at %s",
                                            brandId, productId, applicationDate)
                                );
                            }
                            return list;
                        }
                ));
    }
}