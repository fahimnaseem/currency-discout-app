package com.example.currency_discount_app.controllers;

import com.example.currency_discount_app.services.CurrencyExchangeService;
import com.example.currency_discount_app.services.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("v1/api")
public class CurrencyDiscountController {

    private final CurrencyExchangeService exchangeService;
    private final DiscountService discountService;

    public CurrencyDiscountController(CurrencyExchangeService exchangeService, DiscountService discountService) {
        this.exchangeService = exchangeService;
        this.discountService = discountService;
    }

    @PostMapping("calculate")
    public Map<String ,Object> calculatePayableAmount(
            @RequestBody Map<String, Object> request){

            double billAmount = (double) request.get("billAmount");
            String userType = (String) request.get("userType");
            boolean isGrocery = (boolean) request.get("isGrocery");
            int customerTenure = (int) request.get("customerTenure");
            String originalCurrency = (String) request.get("originalCurrency");
            String targetCurrency = (String) request.get("targetCurrency");

            //Discounted Amount
            double discountedAmount = discountService.calculateDiscountedAmount(billAmount, userType, isGrocery, customerTenure);

            //Currency conversion
            double exchangeRate = exchangeService.getExchangeRate(originalCurrency, targetCurrency);
            double payableAmount = discountedAmount * exchangeRate;

            return Map.of("discountedAmount", discountedAmount, "payableAmount", payableAmount, "exchangeRate", exchangeRate);
    }
}
