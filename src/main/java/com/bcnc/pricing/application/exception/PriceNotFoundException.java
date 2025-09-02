package com.bcnc.pricing.application.exception;

public class PriceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

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
