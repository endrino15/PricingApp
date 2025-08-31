package com.bcnc.pricing.domain.constants;

import java.time.LocalDateTime;

public class PriceTestConstants {

    public static final long PRODUCT_ID = 35455L;
    public static final long BRAND_ID = 1L;

    public static final String CURRENCY_EUR = "EUR";

    public static final int PRIORITY_0 = 0;
    public static final int PRIORITY_1 = 1;

    // Test 1 -> Lista 1
    public static final LocalDateTime DATE_1 = LocalDateTime.parse("2020-06-14T10:00:00");
    public static final long PRICE_LIST_1 = 1L;
    public static final double PRICE_1 = 35.50;
    public static final LocalDateTime START_DATE_1 = LocalDateTime.parse("2020-06-14T00:00:00");
    public static final LocalDateTime END_DATE_1 = LocalDateTime.parse("2020-12-31T23:59:59");

    // Test 2 -> Lista 2
    public static final LocalDateTime DATE_2 = LocalDateTime.parse("2020-06-14T16:00:00");
    public static final long PRICE_LIST_2 = 2L;
    public static final double PRICE_2 = 25.45;
    public static final LocalDateTime START_DATE_2 = LocalDateTime.parse("2020-06-14T15:00:00");
    public static final LocalDateTime END_DATE_2 = LocalDateTime.parse("2020-06-14T18:30:00");

    // Test 3 -> Lista 1 (igual que test 1, sigue aplicando lista 1)
    public static final LocalDateTime DATE_3 = LocalDateTime.parse("2020-06-14T21:00:00");
    public static final long PRICE_LIST_3 = 1L;
    public static final double PRICE_3 = 35.50;
    public static final LocalDateTime START_DATE_3 = LocalDateTime.parse("2020-06-14T00:00:00");
    public static final LocalDateTime END_DATE_3 = END_DATE_1;

    // Test 4 -> Lista 3
    public static final LocalDateTime DATE_4 = LocalDateTime.parse("2020-06-15T10:00:00");
    public static final long PRICE_LIST_4 = 3L;
    public static final double PRICE_4 = 30.50;
    public static final LocalDateTime START_DATE_4 = LocalDateTime.parse("2020-06-15T00:00:00");
    public static final LocalDateTime END_DATE_4 = LocalDateTime.parse("2020-06-15T11:00:00");

    // Test 5 -> Lista 4
    public static final LocalDateTime DATE_5 = LocalDateTime.parse("2020-06-16T21:00:00");
    public static final long PRICE_LIST_5 = 4L;
    public static final double PRICE_5 = 38.95;
    public static final LocalDateTime START_DATE_5 = LocalDateTime.parse("2020-06-15T16:00:00");
    public static final LocalDateTime END_DATE_5 = LocalDateTime.parse("2020-12-31T23:59:59");

    // Fecha para test de no encontrado
    public static final LocalDateTime DATE_NOT_FOUND = LocalDateTime.parse("2020-01-01T10:00:00");
}
