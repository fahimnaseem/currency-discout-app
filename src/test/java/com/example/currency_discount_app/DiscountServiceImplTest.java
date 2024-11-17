package com.example.currency_discount_app;

import com.example.currency_discount_app.services.DiscountService;
import com.example.currency_discount_app.services.impl.DiscountServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiscountServiceImplTest {

    private final DiscountService discountService = new DiscountServiceImpl();

    @Test
    void testCalculateDiscountedAmount_employee() {
        double billAmount = 200.0;
        String userType = "employee";
        boolean isGrocery = false;
        int customerTenure = 0;

        double result = discountService.calculateDiscountedAmount(billAmount, userType, isGrocery, customerTenure);

        assertEquals(130.0, result, 0.01); // Employee gets 30% discount
    }

    @Test
    void testCalculateDiscountedAmount_grocery() {
        double billAmount = 200.0;
        String userType = "employee";
        boolean isGrocery = true;
        int customerTenure = 0;

        double result = discountService.calculateDiscountedAmount(billAmount, userType, isGrocery, customerTenure);

        assertEquals(190.0, result, 0.01); // Only flat discount applies
    }

}
