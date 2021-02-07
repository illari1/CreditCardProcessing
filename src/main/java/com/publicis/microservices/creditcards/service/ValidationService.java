package com.publicis.microservices.creditcards.service;

import com.publicis.microservices.creditcards.domain.error.CardErrorResponse;
import com.publicis.microservices.creditcards.domain.exception.InvalidCardException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    void validateCardNumber (String carNumber) throws InvalidCardException {

        String message = "";
        String code = "";
        boolean isThereException = false;

        if (carNumber.isEmpty() || carNumber.length() > 19) {
            code = "invalid-card-length";
            message = "Invalid card length.";
            isThereException = true;
        }

        else if (!onlyDigits(carNumber)) {
            code = "invalid-card-format";
            message = "The card number provided must contain only digits.";
            isThereException = true;
        }

        else if (!validateLuhn (carNumber)) {
            code = "card-number-validation-error";
            message = "The card number provided is not valid";
            isThereException = true;
        }

        if (isThereException) {
            CardErrorResponse cardErrorResponse = CardErrorResponse.builder()
                    .message(message)
                    .code(code)
                    .status(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                    .build();

            throw new InvalidCardException(cardErrorResponse);
        }
    }

    /**
     * Starting from the rightmost digit double the value of every second digit, If doubling of a number
     * results in a two digits number, then add the digits of the product(e.g., 16: 1 + 6 = 7)
     * Now take the sum of all the digits
     * If the total modulo 10 is equal to 0, then the number is valid according to the Luhn formula;
     * else it is not valid.
     * @param cardNumber
     * @return
     */
    public static boolean  validateLuhn(String cardNumber) {

        int numberDigits = cardNumber.length();
        boolean isEven= false;
        int sum = 0;

        for (int i = numberDigits - 1; i >= 0; i--)
        {

            int number = Character.getNumericValue(cardNumber.charAt(i));

            if (isEven == true) {
                number *= 2;
            }

            sum += number / 10;
            sum += number % 10;

            isEven = !isEven;
        }
        return (sum % 10 == 0);
    }

    public static boolean onlyDigits(String str) {
        int numberDigits = str.length();
        for (int i = 0; i < numberDigits; i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}


