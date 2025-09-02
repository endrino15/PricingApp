package com.bcnc.pricing.infraestructure.rest.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bcnc.pricing.application.exception.PriceNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(PriceNotFoundException.class)
	public ResponseEntity<Map<String, Object>> priceNotFound(PriceNotFoundException ex){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", ex.getMessage()));	
	}

}
