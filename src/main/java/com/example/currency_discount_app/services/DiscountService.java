package com.example.currency_discount_app.services;

public interface DiscountService {
    double calculateDiscountedAmount(double billAmount, String userType, boolean isGrocery, int customerTenure);

}
