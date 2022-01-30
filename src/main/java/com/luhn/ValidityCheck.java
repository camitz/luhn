package com.luhn;

import java.util.stream.*;
import java.util.regex.*;
import java.time.format.*;

public class ValidityCheck
{
    //All number types are checked against this pattern.
    private static Pattern NumberPattern = Pattern.compile("^(?<cent>(1[689]|2\\d)?)(?<year>\\d{2})(?<month>0\\d|1[012]|[2-9]\\d)(?<day>[06][1-9]|[1278]\\d|[39][01]|\\d{2})(?<sep>[-+])?(?<last>\\d{4})$");

    public static Result check(String number)
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
            if(!DateValidator.isValid(dateStr, true))
                return Result.Invalid;
        }
        else if(!DateValidator.isValid(dateStr, false))
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
        public static boolean isValid(String dateStr, boolean centenary) {
            //Reference date is used for unsepecified century and  depends on +-separator. 
            String refDate = DateTimeFormatter.BASIC_ISO_DATE.format(java.time.LocalDate.now()); 
            if(centenary)
                refDate = DateTimeFormatter.BASIC_ISO_DATE.format(java.time.LocalDate.now().plusYears(-100)); 

            if(dateStr.length()<8) { //Century is not specified and should be added
                dateStr = refDate.substring(0,2)+dateStr;

                if(dateStr.compareTo(refDate)>0) //The date is later then refdate, so move back a century. Note from the spec follows that future pnrs are undefined.
                    dateStr = (Integer.parseInt(refDate.substring(0,2))-1) + dateStr.substring(2);
            } else if(centenary && dateStr.compareTo(refDate) > 0) //Numbers such as 19900118+9811 are invalid (younger than 100 with +-separator).
                return false;

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


