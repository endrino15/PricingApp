package com.bcnc.pricing.infraestructure.persistence.init;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.bcnc.pricing.infraestructure.persistence.entity.PriceEntity;
import com.bcnc.pricing.infraestructure.persistence.jpa.JpaPriceRepository;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class H2DataInitializer implements CommandLineRunner {

    private final JpaPriceRepository repository;

    @Override
    public void run(String... args) {
        repository.saveAll(buildInitialPrices());
    }

    private List<PriceEntity> buildInitialPrices() {
        return List.of(
                createPrice(H2DataConstants.BRAND_ID, H2DataConstants.PRODUCT_ID, 1L,
                        H2DataConstants.START_DATE_1, H2DataConstants.END_DATE_FOREVER,
                        H2DataConstants.PRIORITY_0, H2DataConstants.PRICE_1),

                createPrice(H2DataConstants.BRAND_ID, H2DataConstants.PRODUCT_ID, 2L,
                        H2DataConstants.START_DATE_2, H2DataConstants.END_DATE_2,
                        H2DataConstants.PRIORITY_1, H2DataConstants.PRICE_2),

                createPrice(H2DataConstants.BRAND_ID, H2DataConstants.PRODUCT_ID, 3L,
                        H2DataConstants.START_DATE_3, H2DataConstants.END_DATE_3,
                        H2DataConstants.PRIORITY_1, H2DataConstants.PRICE_3),

                createPrice(H2DataConstants.BRAND_ID, H2DataConstants.PRODUCT_ID, 4L,
                        H2DataConstants.START_DATE_4, H2DataConstants.END_DATE_FOREVER,
                        H2DataConstants.PRIORITY_1, H2DataConstants.PRICE_4)
        );
    }

    private PriceEntity createPrice(long brandId, long productId, long priceList,
                                    LocalDateTime startDate, LocalDateTime endDate,
                                    int priority, double price) {
        return new PriceEntity(0L, brandId, productId, priceList, startDate, endDate,
                priority, price, H2DataConstants.CURRENCY_EUR);
    }
}
