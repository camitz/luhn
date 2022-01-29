package com.luhn;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        for (String number : args) 
            System.out.println( String.format("%s is %s.",number, ValidityCheck.isValid(number)?"valid":"invalid"));
        
    }

}


