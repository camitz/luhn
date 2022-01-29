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
    public void vadlidPersonnr()
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
}
