package com.example.currency_discount_app;

import com.example.currency_discount_app.controllers.CurrencyDiscountController;
import com.example.currency_discount_app.services.CurrencyExchangeService;
import com.example.currency_discount_app.services.DiscountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class CurrencyDiscountControllerTest {

    @Mock
    private CurrencyExchangeService exchangeService;

    @Mock
    private DiscountService discountService;

    @InjectMocks
    private CurrencyDiscountController currencyDiscountController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks

    }

    @Test
    void calculatePayableAmount() {
        // Given
        double billAmount = 100.0;
        String userType = "regular";
        boolean isGrocery = true;
        int customerTenure = 2;
        String originalCurrency = "USD";
        String targetCurrency = "INR";

        // Mocking external service calls
        when(discountService.calculateDiscountedAmount(billAmount, userType, isGrocery, customerTenure)).thenReturn(90.0);
        when(exchangeService.getExchangeRate(originalCurrency, targetCurrency)).thenReturn(75.0);

        // When
        Map<String, Object> response = currencyDiscountController.calculatePayableAmount(Map.of(
                "billAmount", billAmount,
                "userType", userType,
                "isGrocery", isGrocery,
                "customerTenure", customerTenure,
                "originalCurrency", originalCurrency,
                "targetCurrency", targetCurrency
        ));

        // Then
        assertNotNull(response);
        assertEquals(90.0, response.get("discountedAmount"));
        assertEquals(6750.0, response.get("payableAmount"));
        assertEquals(75.0, response.get("exchangeRate"));
    }
}
