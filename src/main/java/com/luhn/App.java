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
        {
            var result = ValidityCheck.check(number);
            System.out.println( String.format("%s is %s.",result.IsValid ?"valid":"invalid"));
        }
    }

}


