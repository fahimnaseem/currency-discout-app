package com.example.currency_discount_app;

import com.example.currency_discount_app.services.CurrencyExchangeService;
import com.example.currency_discount_app.services.impl.CurrencyExchangeServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

 class CurrencyExchangeServiceImplTest {

    private final RestTemplate restTemplate = mock(RestTemplate.class);
    private final CurrencyExchangeService exchangeService = new CurrencyExchangeServiceImpl(
            restTemplate);

    @Test
    void testGetExchangeRate_success() {
        String baseCurrency = "USD";
        String targetCurrency = "EUR";

        // Mock API response
        var mockResponse = Map.of("rates", Map.of("EUR", 0.91));
        when(restTemplate.getForObject(anyString(), eq(Map.class))).thenReturn(mockResponse);

        double rate = exchangeService.getExchangeRate(baseCurrency, targetCurrency);

        assertEquals(0.91, rate, 0.01);
    }

}
