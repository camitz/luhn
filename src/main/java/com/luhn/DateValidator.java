package com.luhn;
import java.time.*;
import java.time.format.*;

public class DateValidator {   
    private final LocalDate _refNow;     

    public DateValidator(){
        _refNow = java.time.LocalDate.now();
    }
    
    public DateValidator(LocalDate refNow){
        _refNow = refNow;
    }

    public boolean isValid(String dateStr, boolean centenary) {
        //Reference date is used for unsepecified century and  depends on +-separator. 
        String refDate = DateTimeFormatter.BASIC_ISO_DATE.format(_refNow); 
        if(centenary)
            refDate = DateTimeFormatter.BASIC_ISO_DATE.format(_refNow.plusYears(-100)); 

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

