package com.bcnc.pricing.domain.model;

import java.time.LocalDateTime;

public class Price {
	private final long brandId;
	private final LocalDateTime startDate;
	private final LocalDateTime endDate;
	private final long priceList;
	private final long productId;
	private final int priority;
	private final double price;
	private final String currency;

	public Price(long brandId, long productId, long priceList, LocalDateTime startDate, LocalDateTime endDate,
			int priority, double price, String currency) {
		this.brandId = brandId;
		this.productId = productId;
		this.priceList = priceList;
		this.startDate = startDate;
		this.endDate = endDate;
		this.priority = priority;
		this.price = price;
		this.currency = currency;
	}

	public long getBrandId() {
		return brandId;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public long getPriceList() {
		return priceList;
	}

	public long getProductId() {
		return productId;
	}

	public int getPriority() {
		return priority;
	}

	public double getPrice() {
		return price;
	}

	public String getCurrency() {
		return currency;
	}

}
