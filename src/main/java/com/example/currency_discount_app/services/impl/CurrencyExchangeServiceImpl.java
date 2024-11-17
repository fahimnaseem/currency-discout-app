package com.example.currency_discount_app.services.impl;

import com.example.currency_discount_app.services.CurrencyExchangeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService {

    @Value("${exchange.api.url}")
    private String apiUrl;

    @Value("${exchange.api.key}")
    private String apiKey;


    private final RestTemplate restTemplate;

    public CurrencyExchangeServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    @Cacheable("exchangeRates")
    public double getExchangeRate(String baseCurrency, String targetCurrency) {
        String url = String.format("%s/%s?apikey=%s", apiUrl, baseCurrency, apiKey);
        var response = restTemplate.getForObject(url, Map.class);
        if (response != null && response.containsKey("rates")) {
            Map<String, Double> rates = (Map<String, Double>) response.get("rates");
            return rates.getOrDefault(targetCurrency, 0.0);
        }
        return 0;
    }
}
