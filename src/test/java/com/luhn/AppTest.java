package com.luhn;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AppTest 
{
    private static DateValidator _dateValidator = new DateValidator(java.time.LocalDate.parse("2070-02-28"));
    @Test
    public void validPersonnrFromUppgift()
    {
        assertEquals(Result.ValidPersonnummer, new ValidityCheck(_dateValidator).check("201701102384"));
        assertEquals(Result.ValidPersonnummer, new ValidityCheck(_dateValidator).check("141206-2380"));
        assertEquals(Result.ValidPersonnummer, new ValidityCheck(_dateValidator).check("20080903-2386"));
        assertEquals(Result.ValidPersonnummer, new ValidityCheck(_dateValidator).check("7101169295"));
        assertEquals(Result.ValidPersonnummer, new ValidityCheck(_dateValidator).check("198107249289"));
        assertEquals(Result.ValidPersonnummer, new ValidityCheck(_dateValidator).check("19021214-9819"));
        assertEquals(Result.ValidPersonnummer, new ValidityCheck(_dateValidator).check("190910199827"));
        assertEquals(Result.ValidPersonnummer, new ValidityCheck(_dateValidator).check("191006089807"));
        assertEquals(Result.ValidPersonnummer, new ValidityCheck(_dateValidator).check("192109099180"));
        assertEquals(Result.ValidPersonnummer, new ValidityCheck(_dateValidator).check("4607137454"));
        assertEquals(Result.ValidPersonnummer, new ValidityCheck(_dateValidator).check("194510168885"));
        assertEquals(Result.ValidPersonnummer, new ValidityCheck(_dateValidator).check("189102279800"));
        assertEquals(Result.ValidPersonnummer, new ValidityCheck(_dateValidator).check("189912299816"));
    }

    @Test
    public void badlyFormattedPnrsPass()
    {
        assertEquals(Result.ValidPersonnummer, new ValidityCheck(_dateValidator).check(" 141206-2380 "));
        assertEquals(Result.ValidPersonnummer, new ValidityCheck(_dateValidator).check("  201701102384  "));
    }

    @Test
    public void centenaryCurveBalls()
    {
        assertEquals(Result.ValidPersonnummer, new ValidityCheck(_dateValidator).check("900118+9811"));
        assertEquals(Result.ValidPersonnummer, new ValidityCheck(_dateValidator).check("2501102384"));
        assertEquals(Result.ValidPersonnummer, new ValidityCheck(_dateValidator).check("250110+2384"));

        assertEquals(Result.ValidPersonnummer, new ValidityCheck(_dateValidator).check("18900118+9811"));
        assertEquals(Result.ValidPersonnummer, new ValidityCheck(_dateValidator).check("18250118+9811"));

        assertEquals(Result.Invalid, new ValidityCheck(_dateValidator).check("19900118+9811"));

        assertEquals(Result.ValidPersonnummer, new ValidityCheck(_dateValidator).check("20000229-1110"));
        assertEquals(Result.Invalid, new ValidityCheck(_dateValidator).check("19000229-1110"));
        assertEquals(Result.ValidPersonnummer, new ValidityCheck(_dateValidator).check("000229-1110"));
        assertEquals(Result.Invalid, new ValidityCheck(_dateValidator).check("000229+1110"));
    }

    @Test
    public void veryBadlyFormattedPnrsDoNotPass()
    {
        assertEquals(Result.Invalid, new ValidityCheck(_dateValidator).check("20170110 2384"));
        assertEquals(Result.Invalid, new ValidityCheck(_dateValidator).check("141206p2380"));
        assertEquals(Result.Invalid, new ValidityCheck(_dateValidator).check("20080903- 2386"));
        assertEquals(Result.Invalid, new ValidityCheck(_dateValidator).check("710116929 5"));

    }

    @Test
    public void curveBallPnrsDoNotPass()
    {
        assertEquals(Result.Invalid, new ValidityCheck(_dateValidator).check("14921206-2380"));
        assertEquals(Result.Invalid, new ValidityCheck(_dateValidator).check("2017011023847"));
    }

    @Test
    public void inValidPersonnrFromUppgift()
    {
        assertEquals(Result.Invalid, new ValidityCheck(_dateValidator).check("201701272394"));
        assertEquals(Result.Invalid, new ValidityCheck(_dateValidator).check("190302299813"));
    }

    @Test
    public void validSamordningsNrFromUppgift()
    {
        assertEquals(Result.ValidSamordningsnummer, new ValidityCheck(_dateValidator).check("190910799824"));
    }

    @Test
    public void validOrgnrNrFromUppgift()
    {
        assertEquals(Result.ValidOrganisationsnummer, new ValidityCheck(_dateValidator).check("16556601-6399"));
        assertEquals(Result.ValidOrganisationsnummer, new ValidityCheck(_dateValidator).check("556614-3185"));
        assertEquals(Result.ValidOrganisationsnummer, new ValidityCheck(_dateValidator).check("262000-1111"));
        assertEquals(Result.ValidOrganisationsnummer, new ValidityCheck(_dateValidator).check("857202-7566"));
    }
}
