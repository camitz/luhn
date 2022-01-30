package com.luhn;

public class App 
{
    public static void main( String[] args )
    {
        for (String number : args) 
        {
            var result = ValidityCheck.check(number);
            if(result.IsValid)
                switch(result.NumberType) {
                    case Personummer:
                        System.out.println(String.format("%s är ett giltigt personnummer.", number));
                        break;
                    case Samordningsnummer:
                        System.out.println(String.format("%s är ett giltigt samordningsnummer.", number));
                        break;
                    case Organisationsnummer:
                        System.out.println(String.format("%s är ett giltigt organisationsnummer.", number));
                        break;
                }
            else
                System.out.println(String.format("%s är INTE giltigt.", number));
            }
    }

}


