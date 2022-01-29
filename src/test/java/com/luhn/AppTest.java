package com.luhn;

import static org.junit.Assert.assertTrue;
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
        assertTrue(ValidityCheck.isValid("201701102384"));
        assertTrue(ValidityCheck.isValid("141206-2380"));
        assertTrue(ValidityCheck.isValid("20080903-2386"));
        assertTrue(ValidityCheck.isValid("7101169295"));
        assertTrue(ValidityCheck.isValid("198107249289"));
        assertTrue(ValidityCheck.isValid("19021214-9819"));
        assertTrue(ValidityCheck.isValid("190910199827"));
        assertTrue(ValidityCheck.isValid("191006089807"));
        assertTrue(ValidityCheck.isValid("192109099180"));
        assertTrue(ValidityCheck.isValid("4607137454"));
        assertTrue(ValidityCheck.isValid("194510168885"));
        assertTrue(ValidityCheck.isValid("900118+9811"));
        assertTrue(ValidityCheck.isValid("189102279800"));
        assertTrue(ValidityCheck.isValid("189912299816"));
    }

    @Test
    public void badlyFormattedPnrsPass()
    {
        assertTrue(ValidityCheck.isValid("  201701102384  "));
        assertTrue(ValidityCheck.isValid("141206-2380"));
        assertTrue(ValidityCheck.isValid("20080903-2386"));
        assertTrue(ValidityCheck.isValid("7101169295"));

    }

    @Test
    public void veryBadlyFormattedPnrsDoNotPass()
    {
        assertFalse(ValidityCheck.isValid("20170110 2384"));
        assertFalse(ValidityCheck.isValid("141206p2380"));
        assertFalse(ValidityCheck.isValid("20080903- 2386"));
        assertFalse(ValidityCheck.isValid("710116929 5"));

    }

    @Test
    public void curveBallPnrsDoNotPass()
    {
        assertFalse(ValidityCheck.isValid("141206-2380"));
        assertFalse(ValidityCheck.isValid("111701102384"));
    }

    @Test
    public void inValidPersonnrFromUppgift()
    {
        assertFalse(ValidityCheck.isValid("201701272394"));
        assertFalse(ValidityCheck.isValid("190302299813"));
    }

    @Test
    public void validSamordningsNrFromUppgift()
    {
        assertTrue(ValidityCheck.isValid("190910799824"));
    }

    @Test
    public void validOrgnrNrFromUppgift()
    {
        assertTrue(ValidityCheck.isValid("556614-3185"));
        assertTrue(ValidityCheck.isValid("16556601-6399"));
        assertTrue(ValidityCheck.isValid("262000-1111"));
        assertTrue(ValidityCheck.isValid("857202-7566"));
    }
}
