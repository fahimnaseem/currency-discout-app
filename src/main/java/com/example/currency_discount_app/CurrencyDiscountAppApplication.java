package com.example.currency_discount_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CurrencyDiscountAppApplication {

	public static void main(String[] args) {

		SpringApplication.run(CurrencyDiscountAppApplication.class, args);
		System.out.println("Project Started");
	}

}
