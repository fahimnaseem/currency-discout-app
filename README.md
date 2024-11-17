# Currency Discount Application

## Description
This application calculates the total payable amount for a bill in a specified currency after applying discounts and currency conversion.

## How to Run
- Clone the repository.
- Configure `application.properties` with the API key for exchange rates.
- Build the project using Maven: `mvn clean install`.
- Run the application: `mvn spring-boot:run`.

## API Details
- Endpoint: `v1/api/calculate`
- Method: `POST`
- Body:
  ```json
  {
      "billAmount": 200.0,
      "userType": "employee",
      "isGrocery": false,
      "customerTenure": 3,
      "originalCurrency": "USD",
      "targetCurrency": "EUR"
  }
