package com.publicis.microservices.creditcards.service;

public class ValidationService {

    /**
     * Starting from the rightmost digit double the value of every second digit, If doubling of a number
     * results in a two digits number, then add the digits of the product(e.g., 16: 1 + 6 = 7)
     * Now take the sum of all the digits
     * If the total modulo 10 is equal to 0, then the number is valid according to the Luhn formula;
     * else it is not valid.
     * @param cardNumber
     * @return
     */
    static boolean validateLuhn(String cardNumber) {

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
}


