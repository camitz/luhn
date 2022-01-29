package com.luhn;

import java.util.stream.*;
import java.util.*;
import java.util.regex.*;

public class ValidityCheck
{
    public static Result isValid(String number)
    {
        try {
            // Pattern pattern = Pattern.compile("")            
            number = clean(number);
            if(number.length()!=10)
                return Result.Invalid;
            if(LuhnCheck(number))
                return Result.ValidPersonnummer;
        } catch (Exception e) {
            return Result.Invalid;
        }
        return Result.Invalid;
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

enum NumberTypes {
    Personummer, Samordningsnummer, Organisationsnummer, Undefined
}

class Result {
    public final boolean IsValid;
    public final NumberTypes NumberType;
    
    private Result(boolean valid, NumberTypes type){
        IsValid = valid;
        NumberType = type;
    }

    public static Result Invalid = new Result(false, NumberTypes.Undefined);
    public static Result ValidPersonnummer = new Result(false, NumberTypes.Personummer);
    public static Result ValidSamordningsnummer = new Result(false, NumberTypes.Samordningsnummer);
    public static Result ValidOrganisationsnummer = new Result(false, NumberTypes.Organisationsnummer);

    public boolean equals(Object o) {
         if (o == this) {
            return true;
        }
 
        if (!(o instanceof Result)) {
            return false;
        }
        
        var r = (Result) o;

        return this.IsValid == r.IsValid && this.NumberType == r.NumberType;
    }
}
