package com.luhn;

import java.util.stream.*;
import java.util.regex.*;

public class ValidityCheck
{
    //All number types are checked against this pattern.
    private static Pattern NumberPattern = Pattern.compile("^(?<cent>(1[689]|2\\d)?)(?<year>\\d{2})(?<month>0\\d|1[012]|[2-9]\\d)(?<day>[06][1-9]|[1278]\\d|[39][01]|\\d{2})(?<sep>[-+])?(?<last>\\d{4})$");

    private DateValidator _dateValidator = new DateValidator();

    public ValidityCheck(){

    }

    public ValidityCheck(DateValidator dateValidator){
        _dateValidator = dateValidator;
    }
    
    public Result check(String number)
    {
        var matcher = NumberPattern.matcher(number.trim());

        if(!matcher.find())
            return Result.Invalid;

        var cent = matcher.group("cent");
        var day = matcher.group("day");
        var month = matcher.group("month");

        if(!LuhnCheck(matcher.group("year") + month + day + matcher.group("last"))) //Luhn check must pass for all number types.
            return Result.Invalid;
        
        if(month.compareTo("20") >= 0) { //Orgnr branch ends here.
            if(!(cent.isEmpty() || cent.compareTo("16") == 0))
                return Result.Invalid;

            return Result.ValidOrganisationsnummer;
        }

        if(day.compareTo("61") >= 0) //Samordnings number must pass date validation provided day-part reduced by 60.
            day = (Integer.parseInt(matcher.group("day")) - 60) + "";

        var dateStr = cent + matcher.group("year") + matcher.group("month") + day;
        
        //For extremely specific cases (leap day cases), the date validation is different for centenaries desgnated with + separator.
        if(matcher.group("sep") != null && matcher.group("sep").compareTo("+") == 0) { 
            if(!_dateValidator.isValid(dateStr, true))
                return Result.Invalid;
        }
        else if(!_dateValidator.isValid(dateStr, false))
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

}