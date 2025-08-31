package com.bcnc.pricing.infraestructure.persistence.init;

import java.time.LocalDateTime;

public class H2DataConstants {

	
	 public static final long BRAND_ID = 1L;
	    public static final long PRODUCT_ID = 35455L;
	    public static final String CURRENCY_EUR = "EUR";

	    // Common end date
	    public static final LocalDateTime END_DATE_FOREVER = LocalDateTime.of(2020, 12, 31, 23, 59, 59);

	    // Start dates for test cases
	    public static final LocalDateTime START_DATE_1 = LocalDateTime.of(2020, 6, 14, 0, 0);
	    public static final LocalDateTime START_DATE_2 = LocalDateTime.of(2020, 6, 14, 15, 0);
	    public static final LocalDateTime END_DATE_2   = LocalDateTime.of(2020, 6, 14, 18, 30);

	    public static final LocalDateTime START_DATE_3 = LocalDateTime.of(2020, 6, 15, 0, 0);
	    public static final LocalDateTime END_DATE_3   = LocalDateTime.of(2020, 6, 15, 11, 0);

	    public static final LocalDateTime START_DATE_4 = LocalDateTime.of(2020, 6, 15, 16, 0);

	    // Prices
	    public static final double PRICE_1 = 35.50;
	    public static final double PRICE_2 = 25.45;
	    public static final double PRICE_3 = 30.50;
	    public static final double PRICE_4 = 38.95;

	    // Priorities
	    public static final int PRIORITY_0 = 0;
	    public static final int PRIORITY_1 = 1;
}
