package com.bcnc.pricing.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PriceControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void test1_at10AM_day14_product35455_brand1() throws Exception {
        mockMvc.perform(get("/price")
                .param("applicationDate", "2020-06-14T10:00:00")
                .param("productId", "35455")
                .param("brandId", "1")
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.priceList", is(1)))
        .andExpect(jsonPath("$.price", is(35.50)));
    }

    @Test
    void test2_at16PM_day14_product35455_brand1() throws Exception {
        mockMvc.perform(get("/price")
                .param("applicationDate", "2020-06-14T16:00:00")
                .param("productId", "35455")
                .param("brandId", "1")
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.priceList", is(2)))
        .andExpect(jsonPath("$.price", is(25.45)));
    }

    @Test
    void test3_at21PM_day14_product35455_brand1() throws Exception {
        mockMvc.perform(get("/price")
                .param("applicationDate", "2020-06-14T21:00:00")
                .param("productId", "35455")
                .param("brandId", "1")
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.priceList", is(1)))
        .andExpect(jsonPath("$.price", is(35.50)));
    }

    @Test
    void test4_at10AM_day15_product35455_brand1() throws Exception {
        mockMvc.perform(get("/price")
                .param("applicationDate", "2020-06-15T10:00:00")
                .param("productId", "35455")
                .param("brandId", "1")
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.priceList", is(3)))
        .andExpect(jsonPath("$.price", is(30.50)));
    }

    @Test
    void test5_at21PM_day16_product35455_brand1() throws Exception {
        mockMvc.perform(get("/price")
                .param("applicationDate", "2020-06-16T21:00:00")
                .param("productId", "35455")
                .param("brandId", "1")
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.priceList", is(4)))
        .andExpect(jsonPath("$.price", is(38.95)));
    }
}
