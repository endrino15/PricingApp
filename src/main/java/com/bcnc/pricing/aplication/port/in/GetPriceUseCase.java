package com.bcnc.pricing.aplication.port.in;

import com.bcnc.pricing.domain.model.Price;
import com.bcnc.pricing.domain.model.PriceCriteria;

public interface GetPriceUseCase {
    Price getApplicablePrice(PriceCriteria priceCriteria);
}
