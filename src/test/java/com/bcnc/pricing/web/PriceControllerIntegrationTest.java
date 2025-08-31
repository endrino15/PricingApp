package com.bcnc.pricing.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.number.IsCloseTo.closeTo;

import com.bcnc.pricing.PricingApplication;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.bcnc.pricing.domain.constants.PriceTestConstants;

import java.time.LocalDateTime;

@SpringBootTest(classes = PricingApplication.class)
@AutoConfigureMockMvc
public class PriceControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	private void assertPrice(LocalDateTime date, long productId, long brandId, long expectedPriceList,
			double expectedPrice) throws Exception {
		// Convertimos LocalDateTime a String en formato ISO
		String dateStr = date.toString();

		mockMvc.perform(get("/price").param("applicationDate", dateStr).param("productId", String.valueOf(productId))
				.param("brandId", String.valueOf(brandId))).andExpect(status().isOk())
				.andExpect(jsonPath("$.productId", is((int) productId)))
				.andExpect(jsonPath("$.brandId", is((int) brandId)))
				.andExpect(jsonPath("$.priceList", is((int) expectedPriceList)))
				.andExpect(jsonPath("$.price", closeTo(expectedPrice, 0.01)));
	}

	@Test
	void testPriceAtDate1() throws Exception {
		assertPrice(PriceTestConstants.DATE_1, PriceTestConstants.PRODUCT_ID, PriceTestConstants.BRAND_ID,
				PriceTestConstants.PRICE_LIST_1, PriceTestConstants.PRICE_1);
	}

	@Test
	void testPriceAtDate2() throws Exception {
		assertPrice(PriceTestConstants.DATE_2, PriceTestConstants.PRODUCT_ID, PriceTestConstants.BRAND_ID,
				PriceTestConstants.PRICE_LIST_2, PriceTestConstants.PRICE_2);
	}

	@Test
	void testPriceAtDate3() throws Exception {
		assertPrice(PriceTestConstants.DATE_3, PriceTestConstants.PRODUCT_ID, PriceTestConstants.BRAND_ID,
				PriceTestConstants.PRICE_LIST_3, PriceTestConstants.PRICE_3);
	}

	@Test
	void testPriceAtDate4() throws Exception {
		assertPrice(PriceTestConstants.DATE_4, PriceTestConstants.PRODUCT_ID, PriceTestConstants.BRAND_ID,
				PriceTestConstants.PRICE_LIST_4, PriceTestConstants.PRICE_4);
	}

	@Test
	void testPriceAtDate5() throws Exception {
		assertPrice(PriceTestConstants.DATE_5, PriceTestConstants.PRODUCT_ID, PriceTestConstants.BRAND_ID,
				PriceTestConstants.PRICE_LIST_5, PriceTestConstants.PRICE_5);
	}
}
