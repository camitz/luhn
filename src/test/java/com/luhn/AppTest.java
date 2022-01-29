package com.luhn;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import com.luhn.*;

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
        assertEquals(Result.ValidPersonnummer, ValidityCheck.isValid("201701102384"));
        assertEquals(Result.ValidPersonnummer, ValidityCheck.isValid("141206-2380"));
        assertEquals(Result.ValidPersonnummer, ValidityCheck.isValid("20080903-2386"));
        assertEquals(Result.ValidPersonnummer, ValidityCheck.isValid("7101169295"));
        assertEquals(Result.ValidPersonnummer, ValidityCheck.isValid("198107249289"));
        assertEquals(Result.ValidPersonnummer, ValidityCheck.isValid("19021214-9819"));
        assertEquals(Result.ValidPersonnummer, ValidityCheck.isValid("190910199827"));
        assertEquals(Result.ValidPersonnummer, ValidityCheck.isValid("191006089807"));
        assertEquals(Result.ValidPersonnummer, ValidityCheck.isValid("192109099180"));
        assertEquals(Result.ValidPersonnummer, ValidityCheck.isValid("4607137454"));
        assertEquals(Result.ValidPersonnummer, ValidityCheck.isValid("194510168885"));
        assertEquals(Result.ValidPersonnummer, ValidityCheck.isValid("900118+9811"));
        assertEquals(Result.ValidPersonnummer, ValidityCheck.isValid("189102279800"));
        assertEquals(Result.ValidPersonnummer, ValidityCheck.isValid("189912299816"));
    }

    @Test
    public void badlyFormattedPnrsPass()
    {
        assertEquals(Result.ValidPersonnummer, ValidityCheck.isValid("  201701102384  "));
        assertEquals(Result.ValidPersonnummer, ValidityCheck.isValid("141206-2380"));
        assertEquals(Result.ValidPersonnummer, ValidityCheck.isValid("20080903-2386"));
        assertEquals(Result.ValidPersonnummer, ValidityCheck.isValid("7101169295"));

    }

    @Test
    public void veryBadlyFormattedPnrsDoNotPass()
    {
        assertEquals(Result.Invalid, ValidityCheck.isValid("20170110 2384"));
        assertEquals(Result.Invalid, ValidityCheck.isValid("141206p2380"));
        assertEquals(Result.Invalid, ValidityCheck.isValid("20080903- 2386"));
        assertEquals(Result.Invalid, ValidityCheck.isValid("710116929 5"));

    }

    @Test
    public void curveBallPnrsDoNotPass()
    {
        assertEquals(Result.Invalid, ValidityCheck.isValid("141206-2380"));
        assertEquals(Result.Invalid, ValidityCheck.isValid("111701102384"));
    }

    @Test
    public void inValidPersonnrFromUppgift()
    {
        assertEquals(Result.Invalid, ValidityCheck.isValid("201701272394"));
        assertEquals(Result.Invalid, ValidityCheck.isValid("190302299813"));
    }

    @Test
    public void validSamordningsNrFromUppgift()
    {
        assertEquals(Result.ValidSamordningsnummer, ValidityCheck.isValid("190910799824"));
    }

    @Test
    public void validOrgnrNrFromUppgift()
    {
        assertEquals(Result.ValidOrganisationsnummer, ValidityCheck.isValid("556614-3185"));
        assertEquals(Result.ValidOrganisationsnummer, ValidityCheck.isValid("16556601-6399"));
        assertEquals(Result.ValidOrganisationsnummer, ValidityCheck.isValid("262000-1111"));
        assertEquals(Result.ValidOrganisationsnummer, ValidityCheck.isValid("857202-7566"));
    }
}
