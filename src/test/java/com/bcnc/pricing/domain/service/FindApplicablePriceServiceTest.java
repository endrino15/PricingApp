package com.bcnc.pricing.domain.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bcnc.pricing.aplication.port.out.LoadPricePort;
import com.bcnc.pricing.application.service.PriceService;
import com.bcnc.pricing.domain.constants.PriceTestConstants;
import com.bcnc.pricing.domain.model.Price;
import com.bcnc.pricing.exception.PriceNotFoundException;

@ExtendWith(MockitoExtension.class)
class PriceServiceTest {

	@Mock
	private LoadPricePort loadPricePort;

	@InjectMocks
	private PriceService priceService;

	private List<Price> mockPrices;

	@BeforeEach
	void setUp() {
//		MockitoAnnotations.openMocks(this);

		// Lista completa de precios de prueba usando constantes
		mockPrices = List.of(
				new Price(PriceTestConstants.BRAND_ID, PriceTestConstants.PRODUCT_ID, PriceTestConstants.PRICE_LIST_1,
						PriceTestConstants.START_DATE_1, PriceTestConstants.END_DATE_1, PriceTestConstants.PRIORITY_0,
						PriceTestConstants.PRICE_1, PriceTestConstants.CURRENCY_EUR),
				new Price(PriceTestConstants.BRAND_ID, PriceTestConstants.PRODUCT_ID, PriceTestConstants.PRICE_LIST_2,
						PriceTestConstants.START_DATE_2, PriceTestConstants.END_DATE_2, PriceTestConstants.PRIORITY_1,
						PriceTestConstants.PRICE_2, PriceTestConstants.CURRENCY_EUR),
				new Price(PriceTestConstants.BRAND_ID, PriceTestConstants.PRODUCT_ID, PriceTestConstants.PRICE_LIST_3,
						PriceTestConstants.START_DATE_3, PriceTestConstants.END_DATE_3, PriceTestConstants.PRIORITY_0, // en
																														// la
																														// tabla
																														// el
																														// price_list=1
																														// tenía
																														// prioridad
																														// 0
						PriceTestConstants.PRICE_3, PriceTestConstants.CURRENCY_EUR),
				new Price(PriceTestConstants.BRAND_ID, PriceTestConstants.PRODUCT_ID, PriceTestConstants.PRICE_LIST_4,
						PriceTestConstants.START_DATE_4, PriceTestConstants.END_DATE_4, PriceTestConstants.PRIORITY_1,
						PriceTestConstants.PRICE_4, PriceTestConstants.CURRENCY_EUR),
				new Price(PriceTestConstants.BRAND_ID, PriceTestConstants.PRODUCT_ID, PriceTestConstants.PRICE_LIST_5,
						PriceTestConstants.START_DATE_5, PriceTestConstants.END_DATE_5, PriceTestConstants.PRIORITY_1,
						PriceTestConstants.PRICE_5, PriceTestConstants.CURRENCY_EUR));

	}

	// Simula el adaptador y filtra solo por vigencia temporal
	private List<Price> filterByDate(LocalDateTime applicationDate) {
		return mockPrices.stream()
				.filter(p -> !p.getStartDate().isAfter(applicationDate) && !p.getEndDate().isBefore(applicationDate))
				.collect(Collectors.toList());
	}

	private Price getPriceForDate(LocalDateTime date) {
		// Simula que el adaptador devuelve solo los precios vigentes
		when(loadPricePort.findPrices(PriceTestConstants.BRAND_ID, PriceTestConstants.PRODUCT_ID, date))
				.thenReturn(filterByDate(date));

		// El service aplicará el PriceSelector internamente
		return priceService.getApplicablePrice(PriceTestConstants.BRAND_ID, PriceTestConstants.PRODUCT_ID, date);
	}
	
	private void assertEqualsPrice(long priceList, long getPriceList, double price, double getPrice) {
		assertEquals(priceList, getPriceList);
		assertEquals(price, getPrice);
	}

	@Test
	void test1_at10AM_day14_shouldReturnPriceList1() {
		Price price = getPriceForDate(PriceTestConstants.DATE_1);		
		assertEqualsPrice(PriceTestConstants.PRICE_LIST_1, price.getPriceList(), PriceTestConstants.PRICE_1, price.getPrice());
	}

	@Test
	void test2_at16PM_day14_shouldReturnPriceList2() {
		Price price = getPriceForDate(PriceTestConstants.DATE_2);		
		assertEqualsPrice(PriceTestConstants.PRICE_LIST_2, price.getPriceList(), PriceTestConstants.PRICE_2, price.getPrice());
	}

	@Test
	void test3_at21PM_day14_shouldReturnPriceList1() {
		Price price = getPriceForDate(PriceTestConstants.DATE_3);	
		assertEqualsPrice(PriceTestConstants.PRICE_LIST_3, price.getPriceList(), PriceTestConstants.PRICE_3, price.getPrice());
	}

	@Test
	void test4_at10AM_day15_shouldReturnPriceList3() {
		Price price = getPriceForDate(PriceTestConstants.DATE_4);		
		assertEqualsPrice(PriceTestConstants.PRICE_LIST_4, price.getPriceList(), PriceTestConstants.PRICE_4, price.getPrice());
	}

	@Test
	void test5_at21PM_day16_shouldReturnPriceList4() {
		Price price = getPriceForDate(PriceTestConstants.DATE_5);	
		assertEqualsPrice(PriceTestConstants.PRICE_LIST_5, price.getPriceList(), PriceTestConstants.PRICE_5, price.getPrice());
	}

	@Test
	void test_noPriceFound_shouldThrowException() {
		LocalDateTime date = PriceTestConstants.DATE_NOT_FOUND;

		when(loadPricePort.findPrices(PriceTestConstants.BRAND_ID, PriceTestConstants.PRODUCT_ID, date))
				.thenReturn(List.of());

		assertThrows(PriceNotFoundException.class, () -> priceService.getApplicablePrice(PriceTestConstants.BRAND_ID,
				PriceTestConstants.PRODUCT_ID, date));
	}
}
