package com.bcnc.pricing.infraestructure.persistence.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "PRICE")
public class PriceEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long brandId;
	private long productId;
	private long priceList;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private int priority;
	private double price;
	private String currency;

	public PriceEntity() {
	}

	public PriceEntity(long id, long brandId, long productId, long priceList, LocalDateTime startDate,
			LocalDateTime endDate, int priority, double price, String currency) {
		this.id = id;
		this.brandId = brandId;
		this.productId = productId;
		this.priceList = priceList;
		this.startDate = startDate;
		this.endDate = endDate;
		this.priority = priority;
		this.price = price;
		this.currency = currency;
	}

	public PriceEntity(Object object, long l, long m, long n, LocalDateTime of, LocalDateTime of2, int i, double d,
			String string) {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getBrandId() {
		return brandId;
	}

	public void setBrandId(long brandId) {
		this.brandId = brandId;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public long getPriceList() {
		return priceList;
	}

	public void setPriceList(long priceList) {
		this.priceList = priceList;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
