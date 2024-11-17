package com.example.currency_discount_app.services.impl;

import com.example.currency_discount_app.services.DiscountService;
import org.springframework.stereotype.Service;

@Service
public class DiscountServiceImpl implements DiscountService {
    @Override
    public double calculateDiscountedAmount(double billAmount, String userType, boolean isGrocery, int customerTenure) {
        double discount = 0.0;

        if (!isGrocery) {
            switch (userType.toLowerCase()) {
                case "employee":
                    discount = 0.30; // 30%
                    break;
                case "affiliate":
                    discount = 0.10; // 10%
                    break;
                default:
                    if (customerTenure > 2) {
                        discount = 0.05; // 5%
                    }
            }
    }
        // Apply percentage discount
        double discountedAmount = billAmount - (billAmount * discount);

        // Apply flat $5 discount for every $100
        int flatDiscounts = (int) (billAmount / 100);
        discountedAmount -= flatDiscounts * 5;
        return discountedAmount;
    }
}
