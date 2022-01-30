package com.luhn;

import java.util.stream.*;
import java.util.regex.*;
import java.time.format.*;

public class ValidityCheck
{
    private static Pattern PnrPattern = Pattern.compile("^(?<cent>(1[689]|2\\d)?)(?<year>\\d{2})(?<month>0\\d|1[012]|[2-9]\\d)(?<day>[06][1-9]|[1278]\\d|[39][01]|\\d{2})[-+]?(?<last>\\d{4})$");

    public static Result check(String number)
    {
        var matcher = PnrPattern.matcher(number.trim());

        if(!matcher.find())
            return Result.Invalid;

        var cent = matcher.group("cent");
        var day = matcher.group("day");
        var month = matcher.group("month");

        if(!LuhnCheck(matcher.group("year") + month + day + matcher.group("last")))
            return Result.Invalid;
        
        if(month.compareTo("20") >= 0) {
            if(!(cent.isEmpty() || cent.compareTo("16") == 0))
                return Result.Invalid;

            return Result.ValidOrganisationsnummer;
        }

        if(day.compareTo("61") >= 0) 
            day = (Integer.parseInt(matcher.group("day")) - 60) + "";

        if(!DateValidator.isValid(cent + matcher.group("year") + matcher.group("month") + day))
            return Result.Invalid;

        if(Integer.parseInt(matcher.group("day")) >= 61)
            return Result.ValidSamordningsnummer;
            
        return Result.ValidPersonnummer;
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

    public static class DateValidator {
        private final static String Today = DateTimeFormatter.BASIC_ISO_DATE.format(java.time.LocalDate.now()); 

        public static boolean isValid(String dateStr) {
            if(dateStr.length()<8)
                dateStr = Today.substring(0,1)+dateStr;

            if(dateStr.compareTo(Today)>0)
                dateStr = (Integer.parseInt(Today.substring(0,1))-1) + dateStr;

            try {
                java.time.LocalDate.parse(dateStr, DateTimeFormatter.BASIC_ISO_DATE);
                return true;
            } catch (DateTimeParseException e) {
                return false;
            }
        }
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
    public static Result ValidPersonnummer = new Result(true, NumberTypes.Personummer);
    public static Result ValidSamordningsnummer = new Result(true, NumberTypes.Samordningsnummer);
    public static Result ValidOrganisationsnummer = new Result(true, NumberTypes.Organisationsnummer);

    @Override
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


