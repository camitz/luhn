package com.luhn;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void validPersonnrFromUppgift()
    {
        assertEquals(Result.ValidPersonnummer, ValidityCheck.check("201701102384"));
        assertEquals(Result.ValidPersonnummer, ValidityCheck.check("141206-2380"));
        assertEquals(Result.ValidPersonnummer, ValidityCheck.check("20080903-2386"));
        assertEquals(Result.ValidPersonnummer, ValidityCheck.check("7101169295"));
        assertEquals(Result.ValidPersonnummer, ValidityCheck.check("198107249289"));
        assertEquals(Result.ValidPersonnummer, ValidityCheck.check("19021214-9819"));
        assertEquals(Result.ValidPersonnummer, ValidityCheck.check("190910199827"));
        assertEquals(Result.ValidPersonnummer, ValidityCheck.check("191006089807"));
        assertEquals(Result.ValidPersonnummer, ValidityCheck.check("192109099180"));
        assertEquals(Result.ValidPersonnummer, ValidityCheck.check("4607137454"));
        assertEquals(Result.ValidPersonnummer, ValidityCheck.check("194510168885"));
        assertEquals(Result.ValidPersonnummer, ValidityCheck.check("189102279800"));
        assertEquals(Result.ValidPersonnummer, ValidityCheck.check("189912299816"));
    }

    @Test
    public void badlyFormattedPnrsPass()
    {
        assertEquals(Result.ValidPersonnummer, ValidityCheck.check(" 141206-2380 "));
        assertEquals(Result.ValidPersonnummer, ValidityCheck.check("  201701102384  "));
    }

    @Test
    public void centenaryCurveBalls()
    {
        assertEquals(Result.ValidPersonnummer, ValidityCheck.check("900118+9811"));
        assertEquals(Result.ValidPersonnummer, ValidityCheck.check("2501102384"));
        assertEquals(Result.ValidPersonnummer, ValidityCheck.check("250110+2384"));

        assertEquals(Result.ValidPersonnummer, ValidityCheck.check("18900118+9811"));
        assertEquals(Result.ValidPersonnummer, ValidityCheck.check("18250118+9811"));

        assertEquals(Result.Invalid, ValidityCheck.check("19900118+9811"));

        assertEquals(Result.ValidPersonnummer, ValidityCheck.check("20000229-1110"));
        assertEquals(Result.Invalid, ValidityCheck.check("19000229-1110"));
        assertEquals(Result.ValidPersonnummer, ValidityCheck.check("000229-1110"));
        assertEquals(Result.Invalid, ValidityCheck.check("000229+1110"));
    }

    @Test
    public void veryBadlyFormattedPnrsDoNotPass()
    {
        assertEquals(Result.Invalid, ValidityCheck.check("20170110 2384"));
        assertEquals(Result.Invalid, ValidityCheck.check("141206p2380"));
        assertEquals(Result.Invalid, ValidityCheck.check("20080903- 2386"));
        assertEquals(Result.Invalid, ValidityCheck.check("710116929 5"));

    }

    @Test
    public void curveBallPnrsDoNotPass()
    {
        assertEquals(Result.Invalid, ValidityCheck.check("14921206-2380"));
        assertEquals(Result.Invalid, ValidityCheck.check("2017011023847"));
    }

    @Test
    public void inValidPersonnrFromUppgift()
    {
        assertEquals(Result.Invalid, ValidityCheck.check("201701272394"));
        assertEquals(Result.Invalid, ValidityCheck.check("190302299813"));
    }

    @Test
    public void validSamordningsNrFromUppgift()
    {
        assertEquals(Result.ValidSamordningsnummer, ValidityCheck.check("190910799824"));
    }

    @Test
    public void validOrgnrNrFromUppgift()
    {
        assertEquals(Result.ValidOrganisationsnummer, ValidityCheck.check("16556601-6399"));
        assertEquals(Result.ValidOrganisationsnummer, ValidityCheck.check("556614-3185"));
        assertEquals(Result.ValidOrganisationsnummer, ValidityCheck.check("262000-1111"));
        assertEquals(Result.ValidOrganisationsnummer, ValidityCheck.check("857202-7566"));
    }
}
