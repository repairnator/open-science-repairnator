package com.revature.tier1;

import static com.revature.tier1.PointsTests.addPoints;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * prompt:
 * Create a method that, given an integer, can 
 * raise each digit to the power of the overall 
 * length and return whether the sum of the powers 
 * is equal to the original integer.
 */
public class Answer4Tests {

    @Test
    public void test4(){
		assertTrue(NumberSumLength.checkNumberPowerLength(1));
		assertFalse(NumberSumLength.checkNumberPowerLength(10));
		assertTrue(NumberSumLength.checkNumberPowerLength(153));
		assertFalse(NumberSumLength.checkNumberPowerLength(1635));
        addPoints(40);
    }
}