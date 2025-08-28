package com.bcnc.pricing.exception;

public class PriceNotFoundException extends RuntimeException {
	public PriceNotFoundException(String msg) {
		super(msg);
	}
	
	public PriceNotFoundException(long productId) {
        super("Product " + productId + " not found");
    }

    public PriceNotFoundException() {
        super("Product not found");
    }
}
