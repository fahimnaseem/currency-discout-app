package com.example.currency_discount_app.services;

public interface CurrencyExchangeService {
    double getExchangeRate(String baseCurrency, String targetCurrency);
}
