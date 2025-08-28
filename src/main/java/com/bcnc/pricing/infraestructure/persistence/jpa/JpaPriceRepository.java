package com.bcnc.pricing.infraestructure.persistence.jpa;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bcnc.pricing.infraestructure.persistence.entity.PriceEntity;

public interface JpaPriceRepository extends JpaRepository<PriceEntity, Long>{
	List<PriceEntity> findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(Long brandId,
			Long productId, LocalDateTime start, LocalDateTime end);
}
