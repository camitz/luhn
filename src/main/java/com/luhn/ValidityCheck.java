package com.luhn;

import java.util.stream.*;
import java.util.*;
import java.util.regex.*;

public class ValidityCheck
{
    public static boolean isValid( String number )
    {
        try {
            // Pattern pattern = Pattern.compile("")            
            number = clean(number);
            if(number.length()!=10)
                return false;
            return LuhnCheck(number);
        } catch (Exception e) {
            return false;
        }

    }

    private static String clean(String number)
    {
        number = number.trim().replace("-", "").replace("+", "");

        if (number.length() > 10)
            number = number.substring(2);

        return number;
    }

    private static boolean LuhnCheck(String number)
    {
        var n = new StringBuilder(number).reverse().toString().toCharArray();

        var t1 =
        IntStream
        .range(0, number.length())
        .map(i -> (n[i]-48) * ((i%2)+1))
        .mapToObj(i -> Integer.toString(i))
        .collect(Collectors.joining());

        var t2 =
        t1.chars()
        .map(i -> i - 48)
        .sum();

        return t2 % 10 == 0;
    }
}